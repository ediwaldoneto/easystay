package br.com.nt.easystay.infrastructure.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateReservation {


    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private ClientRequest client;
    private Long roomId;
    private String paymentTiming;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaymentRequest payment;

}
