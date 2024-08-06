package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.exception.ReservationNotFound;
import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.domain.repository.ReservationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
}
