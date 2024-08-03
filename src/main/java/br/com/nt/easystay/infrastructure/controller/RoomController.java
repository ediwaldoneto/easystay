package br.com.nt.easystay.infrastructure.controller;


import br.com.nt.easystay.domain.service.RoomService;
import br.com.nt.easystay.infrastructure.request.RoomResponse;
import br.com.nt.easystay.infrastructure.response.RoomRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public void create(@Valid @RequestBody RoomRequest request) {
        roomService.save(request);
    }

    @GetMapping("/all")
    public List<RoomResponse> listAll() {
        return roomService.findAll();
    }

    @GetMapping("/available-room")
    public List<RoomResponse> findAvailableRoom() {
        return roomService.findAvailableRoom();
    }

    @GetMapping("/{id}")
    public RoomResponse findById(@RequestParam Long id) {
        return roomService.findById(id);
    }

}
