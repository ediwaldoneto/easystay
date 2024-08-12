package br.com.nt.easystay.infrastructure.mapper.request;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.application.dto.PaymentDTO;
import br.com.nt.easystay.application.dto.ReservationDTO;
import br.com.nt.easystay.domain.model.*;
import br.com.nt.easystay.infrastructure.request.CreateReservation;

public class ReservationRequestMapper {

    private ReservationRequestMapper() {
    }

    public static ReservationDTO toReservationDTO(CreateReservation request) {
        final ClientDTO client = ClientDTO.builder()
                .name(request.getClient().getName())
                .email(request.getClient().getEmail())
                .phoneNumber(request.getClient().getPhoneNumber())
                .build();

        PaymentDTO paymentDTO = null;
        if (request.getPayment() != null) {
            paymentDTO = PaymentDTO.builder()
                    .paymentMethod(request.getPayment().getPaymentMethod())
                    .amount(request.getPayment().getAmount())
                    .cardNumber(request.getPayment().getCardNumber())
                    .cardExpiry(request.getPayment().getCardExpiry())
                    .cardCvc(request.getPayment().getCardCvc())
                    .build();

        }
        return ReservationDTO.builder()
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .client(client)
                .paymentTiming(request.getPaymentTiming())
                .payment(paymentDTO)
                .build();
    }

    public static Reservation toEntity(ReservationDTO reservationDTO) {

        Client client = new Client();
        client.setId(reservationDTO.getClient().getId());
        client.setName(reservationDTO.getClient().getName());
        client.setEmail(reservationDTO.getClient().getEmail());
        client.setCpf(reservationDTO.getClient().getCpf());
        client.setPhoneNumber(reservationDTO.getClient().getPhoneNumber());

        Room room = new Room();
        room.setId(reservationDTO.getRoom().getId());
        room.setNumber(reservationDTO.getRoom().getNumber());
        room.setRoomType(RoomType.valueOf(reservationDTO.getRoom().getRoomType()));

        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setCheckInDate(reservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
        reservation.setStatus(ReservationStatus.fromString(reservationDTO.getStatus()));
        reservation.setPaymentTiming(PaymentTiming.fromString(reservationDTO.getPaymentTiming()));
        reservation.setReservationNumber(reservationDTO.getReservationNumber());
        reservation.setClient(client);
        reservation.setRoom(room);

        return reservation;
    }

}
