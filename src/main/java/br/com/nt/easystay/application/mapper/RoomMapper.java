package br.com.nt.easystay.application.mapper;


import br.com.nt.easystay.application.dto.RoomDTO;
import br.com.nt.easystay.domain.model.Room;
import br.com.nt.easystay.domain.model.RoomType;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    private RoomMapper() {
    }

    public static RoomDTO toDTO(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .number(room.getNumber())
                .roomType(room.getRoomType().name())
                .available(room.isAvailable())
                .build();
    }


    public static Room toEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setNumber(roomDTO.getNumber());
        room.setRoomType(RoomType.fromString(roomDTO.getRoomType()));
        room.setAvailable(roomDTO.isAvailable());
        return room;
    }
}
