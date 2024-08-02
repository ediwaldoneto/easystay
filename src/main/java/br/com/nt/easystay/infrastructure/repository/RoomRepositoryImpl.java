package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.model.Room;
import br.com.nt.easystay.domain.repository.RoomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepositoryImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Room findById(Long id) {
        return entityManager.find(Room.class, id);
    }

    @Transactional
    @Override
    public void save(Room room) {
        if (room.getId() == null) {
            entityManager.persist(room);
        } else {
            entityManager.merge(room);
        }
    }


    @Override
    public List<Room> findAll() {
        return entityManager
                .createQuery("from Room", Room.class)
                .getResultList();
    }


    @Override
    public List<Room> findAvailableRoom() {
        return entityManager
                .createQuery("SELECT r FROM Room r WHERE r.available = true", Room.class)
                .getResultList();
    }
}
