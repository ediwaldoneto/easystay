package br.com.nt.easystay.infrastructure.mapper.response;


import br.com.nt.easystay.application.dto.RoomDTO;
import br.com.nt.easystay.infrastructure.response.RoomResponse;
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

    public static RoomDTO toRoomDTO(RoomResponse roomResponse) {
        return RoomDTO.builder()
                .id(roomResponse.getId())
                .number(roomResponse.getNumber())
                .roomType(roomResponse.getRoomType())
                .available(roomResponse.isAvailable())
                .build();
    }
}
