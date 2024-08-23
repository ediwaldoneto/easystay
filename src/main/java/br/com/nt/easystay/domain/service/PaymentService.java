package br.com.nt.easystay.domain.service;

import br.com.nt.easystay.domain.model.Payment;
import br.com.nt.easystay.infrastructure.response.PaymentResponse;

import java.util.Optional;

public interface PaymentService {

    Optional<PaymentResponse> locatePaymentByReservation(final String reservationId);

    void updatePayment(Payment payment);
}
