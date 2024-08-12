package br.com.nt.easystay.domain.service;

import br.com.nt.easystay.domain.model.Room;
import br.com.nt.easystay.infrastructure.response.RoomResponse;
import br.com.nt.easystay.infrastructure.request.RoomRequest;

import java.util.List;

public interface RoomService {

    RoomResponse findById(final Long id);

    void save(final RoomRequest request);

    void save(final Room room);

    List<RoomResponse> findAll();

    List<RoomResponse> findAvailableRoom();

    void delete(final Long id);

    void update(final Long id, final boolean status);

}
