package br.com.nt.easystay.domain.repository;

import br.com.nt.easystay.domain.model.Room;

import java.util.List;
import java.util.Optional;


public interface RoomRepository {

    Optional<Room> findById(final Long id);

    Room save(final Room room);

    List<Room> findAll();

    List<Room> findAvailableRoom();

    void delete(Room room);

    int update(final Long id, final boolean status);

}
