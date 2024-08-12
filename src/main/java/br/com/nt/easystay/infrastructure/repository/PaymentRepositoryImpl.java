package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.model.Payment;
import br.com.nt.easystay.domain.repository.PaymentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Payment> locatePaymentByReservation(String reservationNumber) {
        try {
            Payment payment = entityManager.createQuery(
                            "SELECT p FROM Payment p WHERE p.reservation.reservationNumber = :reservationNumber", Payment.class)
                    .setParameter("reservationNumber", reservationNumber.trim())
                    .getSingleResult();
            return Optional.of(payment);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updatePayment(Payment payment) {
        if (payment.getId() != null) {
            entityManager.createQuery(
                            "UPDATE Payment p " +
                                    "SET p.amount = :amount, " +
                                    "p.paymentMethod = :paymentMethod, " +
                                    "p.reservation = :reservation " +
                                    "WHERE p.id = :id")
                    .setParameter("amount", payment.getAmount())
                    .setParameter("paymentMethod", payment.getPaymentMethod())
                    .setParameter("reservation", payment.getReservation())
                    .setParameter("id", payment.getId())
                    .executeUpdate();
        } else {
            entityManager.persist(payment);
        }
    }
}
