package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.exception.RoomNotFoundException;
import br.com.nt.easystay.domain.model.Room;
import br.com.nt.easystay.domain.repository.RoomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class RoomRepositoryImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String ROOM_NOT_FOUND = "Room not found with id ";


    @Override
    public Room findById(Long id) {
        final Room room = entityManager.find(Room.class, id);
        if (room != null) {
            return room;
        } else {
            throw new RoomNotFoundException(ROOM_NOT_FOUND + id);
        }
    }

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


    @Override
    public void delete(Long id) {
        final Room room = findById(id);
        entityManager.remove(room);
    }

    @Override
    public void update(Long id, boolean status) {
        final Room room = findById(id);
        room.setAvailable(status);
        entityManager.merge(room);
    }
}



