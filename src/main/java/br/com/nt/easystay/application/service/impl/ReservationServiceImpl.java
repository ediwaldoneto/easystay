package br.com.nt.easystay.application.service.impl;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.application.dto.ReservationDTO;
import br.com.nt.easystay.application.mapper.ReservationMapper;
import br.com.nt.easystay.domain.component.ClientComponent;
import br.com.nt.easystay.domain.component.RoomComponent;
import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.domain.model.ReservationStatus;
import br.com.nt.easystay.domain.repository.ReservationRepository;
import br.com.nt.easystay.domain.service.ReservationService;
import br.com.nt.easystay.infrastructure.excpetion.EasyStayException;
import br.com.nt.easystay.infrastructure.mapper.request.ClientRequestMapper;
import br.com.nt.easystay.infrastructure.mapper.request.ReservationRequestMapper;
import br.com.nt.easystay.infrastructure.mapper.response.ReservationResponseMapper;
import br.com.nt.easystay.infrastructure.mapper.response.RoomResponseMapper;
import br.com.nt.easystay.infrastructure.request.ReservationRequest;
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
    private Random random = new Random();

    @Override
    public ReservationResponse findById(Long id) {
        final Reservation reservation = reservationRepository.findById(id);
        return ReservationResponseMapper.toReservationResponse(ReservationMapper.toDTO(reservation));
    }

    @Override
    public void save(Reservation reservation) {
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
    public void update(ReservationRequest request) {
        final ReservationDTO reservationDTO = ReservationRequestMapper.toReservationDTO(request);
        reservationRepository.save(ReservationRequestMapper.toEntity(reservationDTO));
    }

    @Transactional
    @Override
    public void createReservation(ReservationRequest request) {

        final RoomResponse roomResponse = roomComponent.searchForAvailableRoom(request.getRoomId());

        final ClientDTO clientDTO = ClientRequestMapper.toDTO(request.getClient());
        final String clientId = clientComponent.validateAndSaveClient(clientDTO);

        ReservationDTO reservationDTO = ReservationRequestMapper.toReservationDTO(request);
        reservationDTO.setRoom(RoomResponseMapper.toRoomDTO(roomResponse));
        reservationDTO.setStatus(ReservationStatus.PENDING.toString());

        Reservation reservationEntity = ReservationRequestMapper.toEntity(reservationDTO);
        reservationEntity.getClient().setId(clientId);
        reservationEntity.setReservationNumber(generateUniqueReservationNumber());
        save(reservationEntity);
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

