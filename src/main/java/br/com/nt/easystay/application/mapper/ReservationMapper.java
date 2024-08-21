package br.com.nt.easystay.application.mapper;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.application.dto.PaymentDTO;
import br.com.nt.easystay.application.dto.ReservationDTO;
import br.com.nt.easystay.application.dto.RoomDTO;
import br.com.nt.easystay.domain.model.*;

import java.math.BigDecimal;

public class ReservationMapper {

    private ReservationMapper() {

    }

    public static ReservationDTO toDTO(Reservation reservation) {

        PaymentDTO payment = null;
        if (reservation.getPayment() != null) {
            payment = PaymentDTO.builder()
                    .id(reservation.getPayment().getId())
                    .paymentMethod(reservation.getPayment().getPaymentMethod().toString())
                    .amount(reservation.getPayment().getAmount().doubleValue())
                    .cardNumber(reservation.getPayment().getCardNumber())
                    .cardExpiry(reservation.getPayment().getCardExpiry())
                    .cardCvc(reservation.getPayment().getCardCvc())
                    .build();
        }
        final ClientDTO client = ClientDTO.builder()
                .id(reservation.getClient().getId())
                .name(reservation.getClient().getName())
                .email(reservation.getClient().getEmail())
                .cpf(reservation.getClient().getCpf())
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
                .paymentTiming(reservation.getPaymentTiming().toString())
                .status(reservation.getStatus().toString())
                .reservationNumber(reservation.getReservationNumber())
                .client(client)
                .room(room)
                .payment(payment)
                .build();
    }

    public static Reservation toEntity(ReservationDTO reservationDTO) {

        Payment payment = null;
        if (reservationDTO.getPayment() != null) {
            payment = new Payment();
            payment.setId(reservationDTO.getPayment().getId());
            payment.setAmount(BigDecimal.valueOf(reservationDTO.getPayment().getAmount()));
            payment.setCardNumber(reservationDTO.getPayment().getCardNumber());
            payment.setCardCvc(reservationDTO.getPayment().getCardCvc());
            payment.setCardExpiry(reservationDTO.getPayment().getCardExpiry());
            payment.setPaymentMethod(PaymentMethod.fromString(reservationDTO.getPayment().getPaymentMethod()));
        }
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
        reservation.setReservationNumber(reservationDTO.getReservationNumber());
        reservation.setPaymentTiming(PaymentTiming.fromString(reservationDTO.getPaymentTiming()));
        reservation.setClient(client);
        reservation.setRoom(room);
        reservation.setPayment(payment);

        return reservation;
    }
}
