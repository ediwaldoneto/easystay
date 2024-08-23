package br.com.nt.easystay.infrastructure.repository.impl;

import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.domain.model.ReservationStatus;
import br.com.nt.easystay.domain.repository.ReservationRepository;
import br.com.nt.easystay.infrastructure.repository.JpaReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final JpaReservationRepository reservationRepository;

    @Override
    public Optional<Reservation> findById(String id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    @Override
    public void update(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> findByReservationNumber(String reservationNumber) {
        return reservationRepository.findByReservationNumber(reservationNumber);
    }

    @Override
    public boolean existsByReservationNumber(String reservationNumber) {
        return reservationRepository.existsByReservationNumber(reservationNumber);
    }

    @Override
    public Optional<Reservation> findByReservationNumberAndStatus(String reservationNumber, ReservationStatus status) {
        return reservationRepository.findByReservationNumberAndStatus(reservationNumber, status);
    }
}
