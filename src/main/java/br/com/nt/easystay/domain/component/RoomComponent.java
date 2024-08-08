package br.com.nt.easystay.domain.component;


import br.com.nt.easystay.domain.exception.RoomNotFoundException;
import br.com.nt.easystay.domain.service.RoomService;
import br.com.nt.easystay.infrastructure.response.RoomResponse;
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
}
// fazer um reserva