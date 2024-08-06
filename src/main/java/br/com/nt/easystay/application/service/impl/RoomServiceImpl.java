package br.com.nt.easystay.application.service.impl;

import br.com.nt.easystay.application.mapper.RoomMapper;
import br.com.nt.easystay.domain.model.Room;
import br.com.nt.easystay.domain.repository.RoomRepository;
import br.com.nt.easystay.domain.service.RoomService;
import br.com.nt.easystay.infrastructure.mapper.request.RoomRequestMapper;
import br.com.nt.easystay.infrastructure.mapper.response.RoomResponseMapper;
import br.com.nt.easystay.infrastructure.response.RoomResponse;
import br.com.nt.easystay.infrastructure.request.RoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public RoomResponse findById(Long id) {
        final Room room = roomRepository.findById(id);
        return RoomResponseMapper.toRoomResponse(RoomMapper.toDTO(room));
    }

    @Override
    public void save(RoomRequest request) {
        Room room = RoomMapper.toEntity(RoomRequestMapper.toRoomDTO(request));
        roomRepository.save(room);
    }

    @Override
    public List<RoomResponse> findAll() {
        return roomRepository.findAll().stream()
                .map(RoomMapper::toDTO)
                .map(RoomResponseMapper::toRoomResponse)
                .toList();
    }

    @Override
    public List<RoomResponse> findAvailableRoom() {
        return roomRepository.findAvailableRoom().stream()
                .map(RoomMapper::toDTO)
                .map(RoomResponseMapper::toRoomResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        roomRepository.delete(id);
    }

    @Override
    public void update(Long id, boolean status) {
        roomRepository.update(id, status);
    }
}
