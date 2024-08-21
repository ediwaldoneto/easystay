package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.domain.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaReservationRepository extends JpaRepository<Reservation, String> {

    Optional<Reservation> findByReservationNumber(String reservationNumber);

    boolean existsByReservationNumber(String reservationNumber);

    Optional<Reservation> findByReservationNumberAndStatus(String reservationNumber, ReservationStatus status);


}
