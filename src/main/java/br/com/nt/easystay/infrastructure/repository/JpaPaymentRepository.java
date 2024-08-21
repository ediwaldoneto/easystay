package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaPaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByReservation_ReservationNumber(String reservationNumber);
}
