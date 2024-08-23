package br.com.nt.easystay.application.service.impl;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.application.dto.ReservationDTO;
import br.com.nt.easystay.application.dto.RoomDTO;
import br.com.nt.easystay.application.mapper.PaymentMapper;
import br.com.nt.easystay.application.mapper.ReservationMapper;
import br.com.nt.easystay.domain.component.ClientComponent;
import br.com.nt.easystay.domain.component.PaymentComponent;
import br.com.nt.easystay.domain.component.RoomComponent;
import br.com.nt.easystay.domain.exception.DuplicatePaymentException;
import br.com.nt.easystay.domain.exception.PaymentNotFound;
import br.com.nt.easystay.domain.exception.ReservationNotFound;
import br.com.nt.easystay.domain.model.Payment;
import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.domain.model.ReservationStatus;
import br.com.nt.easystay.domain.repository.ReservationRepository;
import br.com.nt.easystay.domain.service.ReservationService;
import br.com.nt.easystay.infrastructure.excpetion.EasyStayException;
import br.com.nt.easystay.infrastructure.mapper.request.ClientRequestMapper;
import br.com.nt.easystay.infrastructure.mapper.request.PaymentRequestMapper;
import br.com.nt.easystay.infrastructure.mapper.request.ReservationRequestMapper;
import br.com.nt.easystay.infrastructure.mapper.response.ReservationResponseMapper;
import br.com.nt.easystay.infrastructure.request.CheckOutRequest;
import br.com.nt.easystay.infrastructure.request.CreateReservation;
import br.com.nt.easystay.infrastructure.response.DetailsResponseReservation;
import br.com.nt.easystay.infrastructure.response.ReservationResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private static final int MAX_TRIES = 1000;

    private final ReservationRepository reservationRepository;
    private final RoomComponent roomComponent;
    private final ClientComponent clientComponent;
    private final PaymentComponent paymentComponent;
    private final Random random = new Random();

    @Override
    public ReservationDTO findById(String id) {
        return reservationRepository.findById(id)
                .map(ReservationMapper::toDTO)
                .orElseThrow(() -> new ReservationNotFound("Reservation not found for ID or Reservation Number"));
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<ReservationDTO> findAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<Reservation> findByReservationNumber(String reservationNumber) {
        return reservationRepository.findByReservationNumber(reservationNumber);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    @Override
    public void update(CreateReservation request) {
        final ReservationDTO reservationDTO = ReservationRequestMapper.toReservationDTO(request);
        reservationRepository.save(ReservationRequestMapper.toEntity(reservationDTO));
    }

    @Override
    public ReservationResponse findByReservationNumberAndStatus(String reservationNumber, ReservationStatus status) {
        return reservationRepository.findByReservationNumberAndStatus(reservationNumber, status)
                .map(ReservationMapper::toDTO)
                .map(ReservationResponseMapper::toReservationResponse)
                .orElseThrow(() -> new ReservationNotFound("Reservation not found"));
    }

    @Transactional
    @Override
    public DetailsResponseReservation createReservation(CreateReservation request) {
        final RoomDTO roomDTO = roomComponent.searchForAvailableRoom(request.getRoomId());
        roomComponent.updateRoomStatus(request.getRoomId(), Boolean.FALSE);

        ClientDTO clientDTO = ClientRequestMapper.toDTO(request.getClient());
        ClientDTO savedClient = clientComponent.validateAndSaveClient(clientDTO);
        Reservation reservationEntity = createReservationEntity(request, roomDTO, savedClient);

        handlePaymentIfPresent(request, reservationEntity);
        saveReservation(reservationEntity);

        return DetailsResponseReservation.builder().reservationNumber(reservationEntity.getReservationNumber())
                .message("Reservation created successfully").localDateTime(LocalDateTime.now()).build();
    }

    @Transactional
    @Override
    public DetailsResponseReservation finalizeReservation(CheckOutRequest request) {

        final ReservationDTO reservationDTO = findByReservationNumber(request.getReservationNumber())
                .map(ReservationMapper::toDTO)
                .orElseThrow(() -> new ReservationNotFound("Reservation not found"));

        if ("AT_CHECKOUT".equalsIgnoreCase(reservationDTO.getPaymentTiming())) {
            paymentComponent.locatePaymentByReservation(reservationDTO.getReservationNumber()).ifPresentOrElse(
                    payment -> {
                        // Se o pagamento já existe, não faça nada
                        if (payment.getId() != null) {
                            throw new DuplicatePaymentException("Payment has already been registered.");
                        }
                    },
                    () -> {
                        if (request.getPayment() == null) {
                            throw new PaymentNotFound("No payment for reservation was found and no payment provided in request.");
                        } else {
                            //  Salvar o pagamento se não existir
                            Payment pay = PaymentMapper.toEntity(
                                    PaymentRequestMapper.toPaymentDTO(request.getPayment()));

                            pay.setReservation(ReservationMapper.toEntity(reservationDTO));
                            paymentComponent.savePayment(pay);

                            reservationDTO.setStatus(ReservationStatus.CHECKED_OUT.toString());
                            reservationDTO.setPayment(PaymentMapper.toDTO(pay));

                            reservationRepository.save(ReservationMapper
                                    .toEntity(reservationDTO));
                            roomComponent.updateRoomStatus(reservationDTO.getRoom().getId(), Boolean.TRUE);

                        }
                    }
            );
        }
        return DetailsResponseReservation.builder()
                .message("Reservation completed successfully. See you later!")
                .reservationNumber(reservationDTO.getReservationNumber())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    @Transactional
    @Override
    public DetailsResponseReservation confirmReservation(String reservationNumber) {
        final ReservationResponse reservation =
                findByReservationNumberAndStatus(reservationNumber, ReservationStatus.PENDING);

        updateReservationStatus(reservation, ReservationStatus.CHECKED_IN);

        return DetailsResponseReservation.builder()
                .reservationNumber("Reservation confirmed successfully")
                .reservationNumber(reservation.getReservationNumber())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    @Transactional
    @Override
    public DetailsResponseReservation cancelReservation(String reservationNumber) {
        final ReservationResponse reservation =
                findByReservationNumberAndStatus(reservationNumber, ReservationStatus.PENDING);
        updateReservationStatus(reservation, ReservationStatus.CANCELLED);

        return DetailsResponseReservation.builder()
                .reservationNumber("Reservation cancelled successfully")
                .reservationNumber(reservation.getReservationNumber())
                .localDateTime(LocalDateTime.now())
                .build();
    }


    private void updateReservationStatus(ReservationResponse reservationResponse, ReservationStatus status) {
        Reservation reservation = ReservationMapper.toEntity(ReservationResponseMapper.toReservationDTO(reservationResponse));
        reservation.setStatus(status);
        saveReservation(reservation);
        roomComponent.updateRoomStatus(reservation.getRoom().getId(),
                status == ReservationStatus.CHECKED_OUT || status == ReservationStatus.CANCELLED);
    }

    private String generateUniqueReservationNumber() {
        for (int i = 0; i < MAX_TRIES; i++) {
            int number = 100000 + random.nextInt(900000);
            String reservationNumber = Integer.toString(number);
            if (!reservationRepository.existsByReservationNumber(reservationNumber)) {
                return reservationNumber;
            }
        }
        throw new EasyStayException();
    }

    private Reservation createReservationEntity(CreateReservation request, RoomDTO roomDTO, ClientDTO savedClient) {

        ReservationDTO reservationDTO = ReservationRequestMapper.toReservationDTO(request);
        reservationDTO.setRoom(roomDTO);
        reservationDTO.setStatus(ReservationStatus.PENDING.toString());
        reservationDTO.setClient(savedClient);

        Reservation reservationEntity = ReservationRequestMapper.toEntity(reservationDTO);
        reservationEntity.setId(UUID.randomUUID().toString());
        reservationEntity.setReservationNumber(generateUniqueReservationNumber());
        return reservationEntity;
    }

    private void handlePaymentIfPresent(CreateReservation request, Reservation reservationEntity) {
        if (request.getPayment() != null) {
            Payment paymentEntity = PaymentMapper.toEntity(PaymentRequestMapper.toPaymentDTO(request.getPayment()));
            paymentEntity.setReservation(reservationEntity);
            reservationEntity.setPayment(paymentEntity);
            paymentComponent.savePayment(paymentEntity);
        }
    }
}