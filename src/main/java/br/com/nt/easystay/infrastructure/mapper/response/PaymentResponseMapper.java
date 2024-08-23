package br.com.nt.easystay.infrastructure.mapper.response;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.application.dto.PaymentDTO;
import br.com.nt.easystay.application.dto.ReservationDTO;
import br.com.nt.easystay.application.dto.RoomDTO;
import br.com.nt.easystay.infrastructure.response.ClientResponse;
import br.com.nt.easystay.infrastructure.response.PaymentResponse;
import br.com.nt.easystay.infrastructure.response.ReservationResponse;
import br.com.nt.easystay.infrastructure.response.RoomResponse;

import java.math.BigDecimal;

public class PaymentResponseMapper {

    private PaymentResponseMapper() {
    }

    public static PaymentResponse toPaymentResponse(final PaymentDTO paymentDTO) {

        final ClientResponse client = ClientResponse.builder()
                .id(paymentDTO.getReservation().getClient().getId())
                .name(paymentDTO.getReservation().getClient().getName())
                .email(paymentDTO.getReservation().getClient().getEmail())
                .phoneNumber(paymentDTO.getReservation().getClient().getPhoneNumber())
                .build();

        final RoomResponse room = RoomResponse.builder()
                .id(paymentDTO.getReservation().getRoom().getId())
                .number(paymentDTO.getReservation().getRoom().getNumber())
                .roomType(paymentDTO.getReservation().getRoom().getRoomType())
                .available(paymentDTO.getReservation().getRoom().isAvailable())
                .build();

        final ReservationResponse reservation = ReservationResponse.builder()
                .id(paymentDTO.getReservation().getId())
                .checkInDate(paymentDTO.getReservation().getCheckInDate())
                .checkOutDate(paymentDTO.getReservation().getCheckOutDate())
                .status(paymentDTO.getReservation().getStatus())
                .reservationNumber(paymentDTO.getReservation().getReservationNumber())
                .paymentTiming(paymentDTO.getReservation().getPaymentTiming())
                .room(room)
                .client(client)
                .build();

        return PaymentResponse.builder()
                .id(paymentDTO.getId())
                .amount(BigDecimal.valueOf(paymentDTO.getAmount()))
                .paymentMethod(paymentDTO.getPaymentMethod())
                .reservation(reservation)
                .build();
    }

    public static PaymentDTO toPaymentDTO(final PaymentResponse paymentResponse) {
        final ClientDTO client = ClientDTO.builder()
                .id(paymentResponse.getReservation().getClient().getId())
                .name(paymentResponse.getReservation().getClient().getName())
                .email(paymentResponse.getReservation().getClient().getEmail())
                .phoneNumber(paymentResponse.getReservation().getClient().getPhoneNumber())
                .build();

        final RoomDTO room = RoomDTO.builder()
                .id(paymentResponse.getReservation().getRoom().getId())
                .number(paymentResponse.getReservation().getRoom().getNumber())
                .roomType(paymentResponse.getReservation().getRoom().getRoomType())
                .available(paymentResponse.getReservation().getRoom().isAvailable())
                .build();

        final ReservationDTO reservation = ReservationDTO.builder()
                .id(paymentResponse.getReservation().getId())
                .checkInDate(paymentResponse.getReservation().getCheckInDate())
                .checkOutDate(paymentResponse.getReservation().getCheckOutDate())
                .status(paymentResponse.getReservation().getStatus())
                .reservationNumber(paymentResponse.getReservation().getReservationNumber())
                .paymentTiming(paymentResponse.getReservation().getPaymentTiming())
                .room(room)
                .client(client)
                .build();

        return PaymentDTO.builder()
                .id(paymentResponse.getId())
                .amount(paymentResponse.getAmount().doubleValue())
                .paymentMethod(paymentResponse.getPaymentMethod())
                .reservation(reservation)
                .build();
    }
}
