package br.com.nt.easystay.domain.repository;

import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.domain.model.ReservationStatus;

import java.util.List;
import java.util.Optional;


public interface ReservationRepository {

    Optional<Reservation> findById(final String id);

    Reservation save(final Reservation reservation);

    List<Reservation> findAll();

    void delete(final Reservation reservation);

    void update(final Reservation reservation);

    Optional<Reservation> findByReservationNumber(final String reservationNumber);

    boolean existsByReservationNumber(String reservationNumber);

    Optional<Reservation> findByReservationNumberAndStatus(String reservationNumber, ReservationStatus status);
}
