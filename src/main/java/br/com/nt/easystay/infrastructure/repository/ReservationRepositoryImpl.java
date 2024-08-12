package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.exception.ReservationNotFound;
import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.domain.repository.ReservationRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Reservation findById(Long id) {
        final Reservation reservation = entityManager.find(Reservation.class, id);
        if (reservation != null) {
            return reservation;
        } else {
            throw new ReservationNotFound("Reservation not found with id " + id);
        }
    }

    @Override
    public void save(Reservation reservation) {
        if (reservation.getId() == null) {
            entityManager.persist(reservation);
        } else {
            entityManager.merge(reservation);
        }
    }

    @Override
    public List<Reservation> findAll() {
        return entityManager
                .createQuery("from Reservation", Reservation.class)
                .getResultList();
    }

    @Override
    public void delete(Long id) {
        final Reservation reservation = findById(id);
        entityManager.remove(reservation);
    }

    @Override
    public void update(Reservation reservation) {
        findById(reservation.getId());
        entityManager.merge(reservation);
    }

    @Override
    public Reservation findByReservationNumber(String reservationNumber) {
        final Reservation reservation = entityManager.find(Reservation.class, reservationNumber);
        if (reservation != null) {
            return reservation;
        } else {
            throw new ReservationNotFound("Reservation not found with reservationNumber " + reservationNumber);
        }
    }

    @Override
    public boolean existsByReservationNumber(String reservationNumber) {
        Query query = entityManager.createQuery("SELECT COUNT(r) FROM Reservation r WHERE r.reservationNumber = :reservationNumber");
        query.setParameter("reservationNumber", reservationNumber);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

    @Override
    public Reservation findReservationByCpfOrReservationNumber(String cpf, String reservationNumber) {
        StringBuilder queryBuilder = new StringBuilder("""
                 SELECT r
                 FROM Reservation r
                 JOIN FETCH r.client c
                 JOIN FETCH r.room rm
                 WHERE 1 = 1
                """);

        if (reservationNumber != null && !reservationNumber.isEmpty()) {
            queryBuilder.append(" AND r.reservationNumber = :reservationNumber");
        }

        if (cpf != null && !cpf.isEmpty()) {
            queryBuilder.append(" AND c.cpf = :cpf");
        }

        TypedQuery<Reservation> query = entityManager.createQuery(queryBuilder.toString(), Reservation.class);

        if (reservationNumber != null && !reservationNumber.isEmpty()) {
            query.setParameter("reservationNumber", reservationNumber);
        }

        if (cpf != null && !cpf.isEmpty()) {
            query.setParameter("cpf", cpf);
        }

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new ReservationNotFound("reservation not found");
        }

    }
}
