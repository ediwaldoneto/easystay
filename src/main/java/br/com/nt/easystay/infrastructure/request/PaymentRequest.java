package br.com.nt.easystay.infrastructure.request;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {


    @Pattern(regexp = "CREDIT_CARD|DEBIT_CARD|CASH", message = "Invalid payment method. Allowed values are: CREDIT_CARD, DEBIT_CARD, CASH")
    private String paymentMethod;

    private Double amount;

    private String cardNumber;

    private String cardExpiry;

    private String cardCvc;
}
