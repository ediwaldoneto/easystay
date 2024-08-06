package br.com.nt.easystay.infrastructure.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {

    private LocalDateTime checkInDate;
    private ClientRequest client;
    private Long roomId;
}
