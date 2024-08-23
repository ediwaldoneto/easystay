package br.com.nt.easystay.infrastructure.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckOutRequest {

    private String reservationNumber;
    private PaymentRequest payment;
}
