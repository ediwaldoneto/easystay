package br.com.nt.easystay.domain.component;


import br.com.nt.easystay.application.dto.RoomDTO;
import br.com.nt.easystay.application.mapper.RoomMapper;
import br.com.nt.easystay.domain.exception.RoomNotFoundException;
import br.com.nt.easystay.domain.model.Room;
import br.com.nt.easystay.domain.model.RoomType;
import br.com.nt.easystay.domain.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RoomComponent {

    private final RoomRepository roomRepository;

    public RoomComponent(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    public RoomDTO searchForAvailableRoom(final Long id) {
        return roomRepository.findById(id)
                .filter(Room::isAvailable)
                .map(RoomMapper::toDTO)
                .orElseThrow(() -> new RoomNotFoundException("Room not available"));
    }

    @Transactional
    public void updateRoomStatus(final Long id, boolean status) {
        final RoomDTO roomDTO = roomRepository.findById(id)
                .map(RoomMapper::toDTO)
                .orElseThrow(() -> new RoomNotFoundException("Room not available"));

        Room room = new Room();
        room.setId(roomDTO.getId());
        room.setNumber(roomDTO.getNumber());
        room.setRoomType(RoomType.valueOf(roomDTO.getRoomType()));
        room.setAvailable(status);
        roomRepository.save(room);
    }
}
