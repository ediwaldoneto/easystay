package br.com.nt.easystay.application.service.impl;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.application.dto.ReservationDTO;
import br.com.nt.easystay.application.mapper.PaymentMapper;
import br.com.nt.easystay.application.mapper.ReservationMapper;
import br.com.nt.easystay.domain.component.ClientComponent;
import br.com.nt.easystay.domain.component.PaymentComponent;
import br.com.nt.easystay.domain.component.RoomComponent;
import br.com.nt.easystay.domain.exception.DuplicatePaymentException;
import br.com.nt.easystay.domain.exception.PaymentNotFound;
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
import br.com.nt.easystay.infrastructure.mapper.response.RoomResponseMapper;
import br.com.nt.easystay.infrastructure.request.CheckOutRequest;
import br.com.nt.easystay.infrastructure.request.CreateReservation;
import br.com.nt.easystay.infrastructure.response.ReservationResponse;
import br.com.nt.easystay.infrastructure.response.RoomResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


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
    public ReservationResponse findById(Long id) {
        final Reservation reservation = reservationRepository.findById(id);
        return ReservationResponseMapper.toReservationResponse(ReservationMapper.toDTO(reservation));
    }

    @Override
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public List<ReservationResponse> findAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationMapper::toDTO)
                .map(ReservationResponseMapper::toReservationResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        reservationRepository.delete(id);
    }

    @Override
    public void update(CreateReservation request) {
        final ReservationDTO reservationDTO = ReservationRequestMapper.toReservationDTO(request);
        reservationRepository.save(ReservationRequestMapper.toEntity(reservationDTO));
    }

    @Override
    public ReservationResponse findReservationByCpfOrReservationNumber(String cpf, String reservationNumber) {
        final Reservation reservation =
                reservationRepository.findReservationByCpfOrReservationNumber(cpf, reservationNumber);
        return ReservationResponseMapper.toReservationResponse(ReservationMapper.toDTO(reservation));
    }

    @Transactional
    @Override
    public String createReservation(CreateReservation request) {
        final RoomResponse roomResponse = roomComponent.searchForAvailableRoom(request.getRoomId());
        final ClientDTO clientDTO = ClientRequestMapper.toDTO(request.getClient());
        final String clientId = clientComponent.validateAndSaveClient(clientDTO);
        roomComponent.updateRoomStatus(request.getRoomId(), Boolean.FALSE);

        ReservationDTO reservationDTO = ReservationRequestMapper.toReservationDTO(request);
        reservationDTO.setRoom(RoomResponseMapper.toRoomDTO(roomResponse));
        reservationDTO.setStatus(ReservationStatus.PENDING.toString());

        Reservation reservationEntity = ReservationRequestMapper.toEntity(reservationDTO);
        reservationEntity.getClient().setId(clientId);
        reservationEntity.setReservationNumber(generateUniqueReservationNumber());

        if (request.getPayment() != null) {

            Payment pay = PaymentMapper.toEntity(
                    PaymentRequestMapper.toPaymentDTO(request.getPayment()));
            pay.setReservation(reservationEntity);
            paymentComponent.savePayment(pay);
        }
        saveReservation(reservationEntity);

        return reservationEntity.getReservationNumber();
    }

    @Transactional
    @Override
    public String finalizeReservation(CheckOutRequest request) {
        final ReservationResponse reservation =
                findReservationByCpfOrReservationNumber(request.getCpf(), request.getReservationNumber());
        if ("AT_CHECKOUT".equalsIgnoreCase(reservation.getPaymentTiming())) {
            paymentComponent.locatePaymentByReservation(reservation.getReservationNumber()).ifPresentOrElse(
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
                            pay.setReservation(ReservationMapper.toEntity(ReservationResponseMapper.toReservationDTO(reservation)));
                            paymentComponent.savePayment(pay);

                            reservation.setStatus(ReservationStatus.CHECKED_OUT.toString());
                            reservationRepository.save(ReservationMapper
                                    .toEntity(ReservationResponseMapper.toReservationDTO(reservation)));
                            roomComponent.updateRoomStatus(reservation.getRoom().getId(), Boolean.TRUE);

                        }
                    }
            );
        }
        return "Reservation completed successfully. See you later!";
    }

    @Transactional
    @Override
    public String confirmReservation(String reservationNumber) {
        final ReservationResponse reservation =
                findReservationByCpfOrReservationNumber(null, reservationNumber);

        updateReservationStatus(reservation, ReservationStatus.CHECKED_IN);
        return "Reservation confirmed successfully";
    }

    @Transactional
    @Override
    public String cancelReservation(String reservationNumber) {
        final ReservationResponse reservation =
                findReservationByCpfOrReservationNumber(null, reservationNumber);
        updateReservationStatus(reservation, ReservationStatus.CANCELLED);
        return "Reservation cancelled successfully";
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
}