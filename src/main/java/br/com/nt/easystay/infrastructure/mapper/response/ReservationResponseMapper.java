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

public class ReservationResponseMapper {

    private ReservationResponseMapper() {
    }

    public static ReservationResponse toReservationResponse(ReservationDTO reservationDTO) {

        PaymentResponse payment = null;
        if (reservationDTO.getPayment() != null) {
            payment = PaymentResponse.builder()
                    .id(reservationDTO.getPayment().getId())
                    .amount(BigDecimal.valueOf(reservationDTO.getPayment().getAmount()))
                    .paymentMethod(reservationDTO.getPayment().getPaymentMethod())
                    .cardNumber(reservationDTO.getPayment().getCardNumber())
                    .cardCvc(reservationDTO.getPayment().getCardCvc())
                    .cardExpiry(reservationDTO.getPayment().getCardExpiry())
                    .build();
        }

        final ClientResponse client = ClientResponse.builder()
                .id(reservationDTO.getClient().getId())
                .cpf(reservationDTO.getClient().getCpf())
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
                .status(reservationDTO.getStatus())
                .paymentTiming(reservationDTO.getPaymentTiming())
                .reservationNumber(reservationDTO.getReservationNumber())
                .client(client)
                .room(room)
                .payment(payment)
                .build();
    }


    public static ReservationDTO toReservationDTO(ReservationResponse reservationResponse) {


        PaymentDTO payment = null;
        if (reservationResponse.getPayment() != null) {
            payment = PaymentDTO.builder()
                    .id(reservationResponse.getPayment().getId())
                    .amount(reservationResponse.getPayment().getAmount().doubleValue())
                    .paymentMethod(reservationResponse.getPayment().getPaymentMethod())
                    .cardNumber(reservationResponse.getPayment().getCardNumber())
                    .cardCvc(reservationResponse.getPayment().getCardCvc())
                    .cardExpiry(reservationResponse.getPayment().getCardExpiry())
                    .build();
        }

        final ClientDTO client = ClientDTO.builder()
                .id(reservationResponse.getClient().getId())
                .name(reservationResponse.getClient().getName())
                .email(reservationResponse.getClient().getEmail())
                .cpf(reservationResponse.getClient().getCpf())
                .phoneNumber(reservationResponse.getClient().getPhoneNumber())
                .build();

        final RoomDTO room = RoomDTO.builder()
                .id(reservationResponse.getRoom().getId())
                .number(reservationResponse.getRoom().getNumber())
                .roomType(reservationResponse.getRoom().getRoomType())
                .available(reservationResponse.getRoom().isAvailable())
                .build();

        return ReservationDTO.builder()
                .id(reservationResponse.getId())
                .checkInDate(reservationResponse.getCheckInDate())
                .checkOutDate(reservationResponse.getCheckOutDate())
                .status(reservationResponse.getStatus())
                .paymentTiming(reservationResponse.getPaymentTiming())
                .reservationNumber(reservationResponse.getReservationNumber())
                .client(client)
                .room(room)
                .payment(payment)
                .build();
    }
}
