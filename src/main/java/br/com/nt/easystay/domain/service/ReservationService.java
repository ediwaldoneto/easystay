package br.com.nt.easystay.domain.service;

import br.com.nt.easystay.application.dto.ReservationDTO;
import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.domain.model.ReservationStatus;
import br.com.nt.easystay.infrastructure.request.CheckOutRequest;
import br.com.nt.easystay.infrastructure.request.CreateReservation;
import br.com.nt.easystay.infrastructure.response.DetailsResponseReservation;
import br.com.nt.easystay.infrastructure.response.ReservationResponse;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    ReservationDTO findById(final String id);

    Reservation saveReservation(final Reservation reservation);

    List<ReservationDTO> findAll();

    Optional<Reservation> findByReservationNumber(String reservationNumber);

    void delete(final Reservation reservation);

    void update(final CreateReservation request);

    ReservationResponse findByReservationNumberAndStatus(String reservationNumber, ReservationStatus status);

    DetailsResponseReservation createReservation(final CreateReservation request);

    DetailsResponseReservation finalizeReservation(final CheckOutRequest request);

    DetailsResponseReservation confirmReservation(final String reservationNumber);

    DetailsResponseReservation cancelReservation(final String reservationNumber);
}
