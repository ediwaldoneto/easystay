package br.com.nt.easystay.infrastructure.mapper;


import br.com.nt.easystay.application.dto.RoomDTO;
import br.com.nt.easystay.infrastructure.request.RoomResponse;
import org.springframework.stereotype.Component;

@Component
public class RoomResponseMapper {

    private RoomResponseMapper() {
    }

    public static RoomResponse toRoomResponse(RoomDTO roomDTO) {
        return RoomResponse.builder()
                .id(roomDTO.getId())
                .number(roomDTO.getNumber())
                .roomType(roomDTO.getRoomType())
                .available(roomDTO.isAvailable())
                .build();
    }
}
