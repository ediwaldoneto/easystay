package br.com.nt.easystay.domain.repository;

import br.com.nt.easystay.domain.model.Reservation;

import java.util.List;

public interface ReservationRepository {

    Reservation findById(final Long id);

    void save(final Reservation reservation);

    List<Reservation> findAll();

    void delete(final Long id);

    void update(final Reservation reservation);

    Reservation findByReservationNumber(final String reservationNumber);

    boolean existsByReservationNumber(String reservationNumber);
}
