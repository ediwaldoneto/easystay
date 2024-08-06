package br.com.nt.easystay.infrastructure.mapper.request;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.application.dto.ReservationDTO;
import br.com.nt.easystay.domain.model.Client;
import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.domain.model.Room;
import br.com.nt.easystay.domain.model.RoomType;
import br.com.nt.easystay.infrastructure.request.ReservationRequest;

public class ReservationRequestMapper {

    private ReservationRequestMapper() {
    }

    public static ReservationDTO toReservationDTO(ReservationRequest request) {
        final ClientDTO client = ClientDTO.builder()
                .name(request.getClient().getName())
                .email(request.getClient().getEmail())
                .phoneNumber(request.getClient().getPhoneNumber())
                .build();

        return ReservationDTO.builder()
                .checkInDate(request.getCheckInDate())
                .client(client)
                .build();
    }

    public static Reservation toEntity(ReservationDTO reservationDTO) {

        Client client = new Client();
        client.setId(reservationDTO.getClient().getId());
        client.setName(reservationDTO.getClient().getName());
        client.setEmail(reservationDTO.getClient().getEmail());
        client.setPhoneNumber(reservationDTO.getClient().getPhoneNumber());

        Room room = new Room();
        room.setId(reservationDTO.getRoom().getId());
        room.setNumber(reservationDTO.getRoom().getNumber());
        room.setRoomType(RoomType.valueOf(reservationDTO.getRoom().getRoomType()));

        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setCheckInDate(reservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
        reservation.setClient(client);
        reservation.setRoom(room);

        return reservation;
    }

}
