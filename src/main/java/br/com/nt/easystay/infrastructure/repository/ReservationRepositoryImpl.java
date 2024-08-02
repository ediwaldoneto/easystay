package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.domain.repository.ReservationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Reservation findById(Long id) {
        return entityManager.find(Reservation.class, id);
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
}
