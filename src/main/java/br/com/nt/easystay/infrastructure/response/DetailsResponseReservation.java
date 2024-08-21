package br.com.nt.easystay.infrastructure.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DetailsResponseReservation {

    private String reservationNumber;
    private String message;
    private LocalDateTime localDateTime;
}
