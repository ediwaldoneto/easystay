package br.com.nt.easystay.infrastructure.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentResponse {

    private Long id;
    private String paymentMethod;
    private BigDecimal amount;
    private String cardNumber;
    private String cardExpiry;
    private String cardCvc;
    private ReservationResponse reservation;
}
