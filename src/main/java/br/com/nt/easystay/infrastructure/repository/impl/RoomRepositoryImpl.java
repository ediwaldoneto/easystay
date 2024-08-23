package br.com.nt.easystay.infrastructure.repository.impl;

import br.com.nt.easystay.domain.model.Room;
import br.com.nt.easystay.domain.repository.RoomRepository;
import br.com.nt.easystay.infrastructure.repository.JpaRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepository {

    private final JpaRoomRepository roomRepository;

    @Override
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.saveAndFlush(room);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findAvailableRoom() {
        return roomRepository.findAll();
    }

    @Override
    public void delete(Room room) {
        roomRepository.delete(room);
    }

    @Override
    public int update(Long id, boolean status) {
        return roomRepository.updateAvailableById(status, id);
    }
}
