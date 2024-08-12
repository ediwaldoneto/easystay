package br.com.nt.easystay.infrastructure.mapper.request;

import br.com.nt.easystay.application.dto.PaymentDTO;
import br.com.nt.easystay.infrastructure.request.PaymentRequest;

public class PaymentRequestMapper {

    private PaymentRequestMapper() {
    }

    public static PaymentDTO toPaymentDTO(PaymentRequest paymentRequest) {

        return PaymentDTO.builder()
                .amount(paymentRequest.getAmount())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .cardNumber(paymentRequest.getCardNumber())
                .cardCvc(paymentRequest.getCardCvc())
                .cardExpiry(paymentRequest.getCardExpiry())
                .build();
    }
}
