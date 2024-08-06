package br.com.nt.easystay.application.mapper;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.application.dto.ReservationDTO;
import br.com.nt.easystay.application.dto.RoomDTO;
import br.com.nt.easystay.domain.model.Client;
import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.domain.model.Room;
import br.com.nt.easystay.domain.model.RoomType;

public class ReservationMapper {

    private ReservationMapper() {

    }

    public static ReservationDTO toDTO(Reservation reservation) {
        final ClientDTO client = ClientDTO.builder()
                .id(reservation.getClient().getId())
                .name(reservation.getClient().getName())
                .email(reservation.getClient().getEmail())
                .phoneNumber(reservation.getClient().getPhoneNumber())
                .build();

        final RoomDTO room = RoomDTO.builder()
                .id(reservation.getRoom().getId())
                .number(reservation.getRoom().getNumber())
                .roomType(reservation.getRoom().getRoomType().toString())
                .build();


        return ReservationDTO.builder()
                .id(reservation.getId())
                .checkInDate(reservation.getCheckInDate())
                .checkOutDate(reservation.getCheckOutDate())
                .client(client)
                .room(room)
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
