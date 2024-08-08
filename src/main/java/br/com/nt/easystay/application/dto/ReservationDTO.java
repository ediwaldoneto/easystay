package br.com.nt.easystay.application.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDTO {

    private Long id;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private String status;
    private ClientDTO client;
    private RoomDTO room;
    private String paymentTiming;
    private PaymentDTO payment;

}

