package br.com.nt.easystay.infrastructure.controller;


import br.com.nt.easystay.domain.service.RoomService;
import br.com.nt.easystay.infrastructure.response.RoomResponse;
import br.com.nt.easystay.infrastructure.request.RoomRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<RoomResponse>> listAll() {
        return new ResponseEntity<>(roomService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/available-room")
    public ResponseEntity<List<RoomResponse>> findAvailableRoom() {
        return new ResponseEntity<>(roomService.findAvailableRoom(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam boolean status) {
        roomService.update(id, status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
