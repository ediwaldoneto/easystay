package br.com.nt.easystay.domain.service;

import br.com.nt.easystay.infrastructure.request.RoomResponse;
import br.com.nt.easystay.infrastructure.response.RoomRequest;

import java.util.List;

public interface RoomService {

    RoomResponse findById(final Long id);

    void save(final RoomRequest request);

    List<RoomResponse> findAll();

    List<RoomResponse> findAvailableRoom();

    void delete(final Long id);

    void update(final Long id, final boolean status);

}
