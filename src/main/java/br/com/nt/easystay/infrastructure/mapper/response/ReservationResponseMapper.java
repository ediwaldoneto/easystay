package br.com.nt.easystay.infrastructure.mapper.response;

import br.com.nt.easystay.application.dto.ReservationDTO;
import br.com.nt.easystay.infrastructure.response.ClientResponse;
import br.com.nt.easystay.infrastructure.response.ReservationResponse;
import br.com.nt.easystay.infrastructure.response.RoomResponse;

public class ReservationResponseMapper {

    private ReservationResponseMapper() {
    }

    public static ReservationResponse toReservationResponse(ReservationDTO reservationDTO) {
        final ClientResponse client = ClientResponse.builder()
                .id(reservationDTO.getClient().getId())
                .name(reservationDTO.getClient().getName())
                .email(reservationDTO.getClient().getEmail())
                .phoneNumber(reservationDTO.getClient().getPhoneNumber())
                .build();

        final RoomResponse room = RoomResponse.builder()
                .id(reservationDTO.getRoom().getId())
                .number(reservationDTO.getRoom().getNumber())
                .roomType(reservationDTO.getRoom().getRoomType())
                .available(reservationDTO.getRoom().isAvailable())
                .build();

        return ReservationResponse.builder()
                .id(reservationDTO.getId())
                .checkInDate(reservationDTO.getCheckInDate())
                .checkOutDate(reservationDTO.getCheckOutDate())
                .client(client)
                .room(room)
                .build();
    }
}
