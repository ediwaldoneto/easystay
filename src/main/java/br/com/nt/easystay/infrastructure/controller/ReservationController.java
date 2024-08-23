package br.com.nt.easystay.infrastructure.controller;

import br.com.nt.easystay.domain.service.ReservationService;
import br.com.nt.easystay.infrastructure.request.CheckOutRequest;
import br.com.nt.easystay.infrastructure.request.CreateReservation;
import br.com.nt.easystay.infrastructure.response.DetailsResponseReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/createReservation")
    public ResponseEntity<DetailsResponseReservation> createReservation(@Valid @RequestBody CreateReservation request) {
        return new ResponseEntity<>(reservationService.createReservation(request), HttpStatus.CREATED);
    }

    @PatchMapping("/cancelReservation/{reservationNumber}")
    public ResponseEntity<DetailsResponseReservation> cancelReservation(@PathVariable("reservationNumber") String reservationNumber) {
        return new ResponseEntity<>(reservationService.cancelReservation(reservationNumber), HttpStatus.OK);
    }

    @PostMapping("/checkIn/{reservationNumber}")
    public ResponseEntity<DetailsResponseReservation> processCheckIn(@PathVariable("reservationNumber") String reservationNumber) {
        return new ResponseEntity<>(reservationService.confirmReservation(reservationNumber), HttpStatus.OK);
    }

    @PostMapping("/checkOut")
    public ResponseEntity<DetailsResponseReservation> processCheckOut(@RequestBody CheckOutRequest request) {
        return new ResponseEntity<>(reservationService.finalizeReservation(request), HttpStatus.OK);
    }
}
