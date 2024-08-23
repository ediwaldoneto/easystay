package br.com.nt.easystay.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDTO {

    private Long id;
    private String paymentMethod;
    private Double amount;
    private String cardNumber;
    private String cardExpiry;
    private String cardCvc;
    private ReservationDTO reservation;
}
