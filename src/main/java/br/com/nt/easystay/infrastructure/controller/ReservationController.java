package br.com.nt.easystay.infrastructure.controller;

import br.com.nt.easystay.domain.service.ReservationService;
import br.com.nt.easystay.infrastructure.request.CheckOutRequest;
import br.com.nt.easystay.infrastructure.request.CreateReservation;
import br.com.nt.easystay.infrastructure.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Validated
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/createReservation")
    public ResponseEntity<String> createReservation(@RequestBody CreateReservation request) {
        return new ResponseEntity<>(reservationService.createReservation(request), HttpStatus.OK);
    }

    @PatchMapping("/cancelReservation/{reservationNumber}")
    public ResponseEntity<String> cancelReservation(@PathVariable("reservationNumber") String reservationNumber) {
        return new ResponseEntity<>(reservationService.cancelReservation(reservationNumber), HttpStatus.OK);
    }

    @PostMapping("/checkIn/{reservationNumber}")
    public ResponseEntity<String> processCheckIn(@PathVariable("reservationNumber") String reservationNumber) {
        return new ResponseEntity<>(reservationService.confirmReservation(reservationNumber), HttpStatus.OK);
    }

    @PostMapping("/checkOut")
    public ResponseEntity<String> processCheckOut(@RequestBody CheckOutRequest request) {
        return new ResponseEntity<>(reservationService.finalizeReservation(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.findById(id), HttpStatus.OK);
    }
}
