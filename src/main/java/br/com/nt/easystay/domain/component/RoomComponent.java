package br.com.nt.easystay.domain.component;


import br.com.nt.easystay.domain.exception.RoomNotFoundException;
import br.com.nt.easystay.domain.model.Room;
import br.com.nt.easystay.domain.model.RoomType;
import br.com.nt.easystay.domain.service.RoomService;
import br.com.nt.easystay.infrastructure.response.RoomResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RoomComponent {

    private final RoomService roomService;

    public RoomComponent(RoomService roomService) {
        this.roomService = roomService;
    }

    public RoomResponse searchForAvailableRoom(final Long id) {
        final RoomResponse room = roomService.findById(id);

        if (room != null && room.isAvailable()) {
            return room;
        } else {
            throw new RoomNotFoundException("room not available");
        }
    }

    @Transactional
    public void updateRoomStatus(final Long id, boolean status) {
        final RoomResponse roomResponse = roomService.findById(id);
        Room room = new Room();
        room.setId(roomResponse.getId());
        room.setNumber(roomResponse.getNumber());
        room.setRoomType(RoomType.valueOf(roomResponse.getRoomType()));
        room.setAvailable(status);
        roomService.save(room);
    }
}
