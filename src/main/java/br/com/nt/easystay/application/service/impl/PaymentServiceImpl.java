package br.com.nt.easystay.application.service.impl;

import br.com.nt.easystay.application.dto.PaymentDTO;
import br.com.nt.easystay.application.mapper.PaymentMapper;
import br.com.nt.easystay.domain.model.Payment;
import br.com.nt.easystay.domain.repository.PaymentRepository;
import br.com.nt.easystay.domain.service.PaymentService;
import br.com.nt.easystay.infrastructure.mapper.response.PaymentResponseMapper;
import br.com.nt.easystay.infrastructure.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Optional<PaymentResponse> locatePaymentByReservation(String reservationId) {
       return paymentRepository.locatePaymentByReservation(reservationId)
               .map(PaymentMapper::toDTO)
               .map(PaymentResponseMapper::toPaymentResponse);

    }

    @Override
    public void updatePayment(Payment payment) {
        paymentRepository.updatePayment(payment);
    }
}
