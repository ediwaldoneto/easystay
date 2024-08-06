package br.com.nt.easystay.domain.component;


import br.com.nt.easystay.domain.service.RoomService;
import br.com.nt.easystay.infrastructure.response.RoomResponse;
import org.springframework.stereotype.Component;

@Component
public class RoomComponent {

    private final RoomService roomService;

    public RoomComponent(RoomService roomService) {
        this.roomService = roomService;
    }

    public RoomResponse searchForRoom(final Long id) {

        return roomService.findById(id);
    }
}
