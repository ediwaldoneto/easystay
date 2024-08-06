package br.com.nt.easystay.infrastructure.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationResponse {

    private Long id;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private ClientResponse client;
    private RoomResponse room;

}
