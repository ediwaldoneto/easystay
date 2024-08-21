package br.com.nt.easystay.infrastructure.repository.impl;

import br.com.nt.easystay.domain.model.Payment;
import br.com.nt.easystay.domain.repository.PaymentRepository;
import br.com.nt.easystay.infrastructure.repository.JpaPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final JpaPaymentRepository paymentRepository;

    @Override
    public Optional<Payment> locatePaymentByReservation(String reservationId) {
        return paymentRepository.findByReservation_ReservationNumber(reservationId);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
