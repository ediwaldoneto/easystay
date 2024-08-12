package br.com.nt.easystay.domain.repository;

import br.com.nt.easystay.domain.model.Payment;

import java.util.Optional;

public interface PaymentRepository {

    Optional<Payment> locatePaymentByReservation(final String reservationId);

    void updatePayment(Payment payment);
}
