package br.com.nt.easystay.application.mapper;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.application.dto.PaymentDTO;
import br.com.nt.easystay.application.dto.ReservationDTO;
import br.com.nt.easystay.application.dto.RoomDTO;
import br.com.nt.easystay.domain.model.*;

import java.math.BigDecimal;

public class PaymentMapper {

    private PaymentMapper() {
    }

    public static PaymentDTO toDTO(Payment payment) {

        final ClientDTO client = ClientDTO.builder()
                .id(payment.getReservation().getClient().getId())
                .name(payment.getReservation().getClient().getName())
                .cpf(payment.getReservation().getClient().getCpf())
                .email(payment.getReservation().getClient().getEmail())
                .phoneNumber(payment.getReservation().getClient().getPhoneNumber())
                .build();

        final RoomDTO room = RoomDTO.builder()
                .id(payment.getReservation().getRoom().getId())
                .number(payment.getReservation().getRoom().getNumber())
                .roomType(payment.getReservation().getRoom().getRoomType().toString())
                .available(payment.getReservation().getRoom().isAvailable())
                .build();

        final ReservationDTO reservation = ReservationDTO.builder()
                .id(payment.getId())
                .status(payment.getReservation().getStatus().toString())
                .checkInDate(payment.getReservation().getCheckInDate())
                .checkOutDate(payment.getReservation().getCheckOutDate())
                .status(payment.getReservation().getStatus().toString())
                .paymentTiming(payment.getReservation().getPaymentTiming().toString())
                .reservationNumber(payment.getReservation().getReservationNumber())
                .client(client)
                .room(room)
                .build();

        return PaymentDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount().doubleValue())
                .paymentMethod(payment.getPaymentMethod().toString())
                .cardNumber(payment.getCardNumber())
                .cardExpiry(payment.getCardExpiry())
                .cardCvc(payment.getCardCvc())
                .reservation(reservation)
                .build();

    }

    public static Payment toEntity(PaymentDTO paymentDTO) {
        Client client = new Client();
        client.setId(paymentDTO.getReservation().getClient().getId());
        client.setName(paymentDTO.getReservation().getClient().getName());
        client.setCpf(paymentDTO.getReservation().getClient().getCpf());
        client.setEmail(paymentDTO.getReservation().getClient().getEmail());
        client.setPhoneNumber(paymentDTO.getReservation().getClient().getPhoneNumber());

        Room room = new Room();
        room.setId(paymentDTO.getReservation().getRoom().getId());
        room.setNumber(paymentDTO.getReservation().getRoom().getNumber());
        room.setRoomType(RoomType.valueOf(paymentDTO.getReservation().getRoom().getRoomType()));
        room.setAvailable(paymentDTO.getReservation().getRoom().isAvailable());

        Reservation reservation = new Reservation();
        reservation.setId(paymentDTO.getReservation().getId());
        reservation.setStatus(ReservationStatus.fromString(paymentDTO.getReservation().getStatus()));
        reservation.setCheckInDate(paymentDTO.getReservation().getCheckInDate());
        reservation.setCheckOutDate(paymentDTO.getReservation().getCheckOutDate());
        reservation.setPaymentTiming(PaymentTiming.valueOf(paymentDTO.getReservation().getPaymentTiming()));
        reservation.setReservationNumber(paymentDTO.getReservation().getReservationNumber());
        reservation.setClient(client);
        reservation.setRoom(room);

        Payment payment = new Payment();
        payment.setId(paymentDTO.getId());
        payment.setAmount(BigDecimal.valueOf(paymentDTO.getAmount()));
        payment.setPaymentMethod(PaymentMethod.valueOf(paymentDTO.getPaymentMethod()));
        payment.setCardNumber(paymentDTO.getCardNumber());
        payment.setCardExpiry(paymentDTO.getCardExpiry());
        payment.setCardCvc(paymentDTO.getCardCvc());
        payment.setReservation(reservation);

        return payment;
    }
}
