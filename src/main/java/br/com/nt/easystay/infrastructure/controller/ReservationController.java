package br.com.nt.easystay.infrastructure.controller;

import br.com.nt.easystay.domain.service.ReservationService;
import br.com.nt.easystay.infrastructure.request.ReservationRequest;
import br.com.nt.easystay.infrastructure.response.ReservationResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Validated
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public void create(@Valid @RequestBody ReservationRequest request) {
        reservationService.createReservation(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.findById(id), HttpStatus.OK);
    }
}
