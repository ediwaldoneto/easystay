package br.com.nt.easystay.application.service.impl;

import br.com.nt.easystay.application.dto.RoomDTO;
import br.com.nt.easystay.application.mapper.RoomMapper;
import br.com.nt.easystay.domain.repository.RoomRepository;
import br.com.nt.easystay.domain.service.RoomService;
import br.com.nt.easystay.infrastructure.mapper.RoomRequestMapper;
import br.com.nt.easystay.infrastructure.mapper.RoomResponseMapper;
import br.com.nt.easystay.infrastructure.request.RoomResponse;
import br.com.nt.easystay.infrastructure.response.RoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public RoomResponse findById(Long id) {
        final RoomDTO dto = RoomMapper.toDTO(roomRepository.findById(id));
        return RoomResponseMapper.toRoomResponse(dto);
    }

    @Override
    public void save(RoomRequest request) {
        final RoomDTO roomDTO = RoomRequestMapper.toRoomDTO(request);
        roomRepository.save(RoomMapper.toEntity(roomDTO));
    }

    @Override
    public List<RoomResponse> findAll() {
        final List<RoomDTO> retVal = roomRepository.findAll().stream()
                .map(RoomMapper::toDTO)
                .toList();
        return retVal.stream().map(RoomResponseMapper::toRoomResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomResponse> findAvailableRoom() {
        final List<RoomDTO> retVal = roomRepository.findAvailableRoom().stream()
                .map(RoomMapper::toDTO)
                .toList();
        return retVal.stream().map(RoomResponseMapper::toRoomResponse)
                .collect(Collectors.toList());
    }
}
