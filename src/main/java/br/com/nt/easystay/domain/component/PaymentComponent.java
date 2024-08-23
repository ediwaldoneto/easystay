package br.com.nt.easystay.domain.component;

import br.com.nt.easystay.domain.model.Payment;
import br.com.nt.easystay.domain.service.PaymentService;
import br.com.nt.easystay.infrastructure.response.PaymentResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentComponent {

    private final PaymentService paymentService;

    public PaymentComponent(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public Optional<PaymentResponse> locatePaymentByReservation(final String reservationNumber) {
        return paymentService.locatePaymentByReservation(reservationNumber);
    }

    @Transactional
    public void savePayment(Payment payment) {
        paymentService.updatePayment(payment);
    }
}
