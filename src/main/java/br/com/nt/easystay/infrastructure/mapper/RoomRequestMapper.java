package br.com.nt.easystay.infrastructure.mapper;

import br.com.nt.easystay.application.dto.RoomDTO;
import br.com.nt.easystay.infrastructure.response.RoomRequest;
import org.springframework.stereotype.Component;

@Component
public class RoomRequestMapper {

    private RoomRequestMapper() {
    }

    public static RoomDTO toRoomDTO(RoomRequest request) {
        return RoomDTO.builder()
                .number(request.getNumber())
                .roomType(request.getRoomType())
                .available(request.isAvailable())
                .build();
    }
}
