package br.com.nt.easystay.domain.repository;

import br.com.nt.easystay.domain.model.Room;

import java.util.List;

public interface RoomRepository {

    Room findById(final Long id);

    void save(final Room room);

    List<Room> findAll();

    List<Room> findAvailableRoom();

}
