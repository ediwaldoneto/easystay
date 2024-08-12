package br.com.nt.easystay.domain.service;

import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.infrastructure.request.CheckOutRequest;
import br.com.nt.easystay.infrastructure.request.CreateReservation;
import br.com.nt.easystay.infrastructure.response.ReservationResponse;

import java.util.List;

public interface ReservationService {

    ReservationResponse findById(final Long id);

    void saveReservation(final Reservation reservation);

    List<ReservationResponse> findAll();

    void delete(final Long id);

    void update(final CreateReservation request);

    ReservationResponse findReservationByCpfOrReservationNumber(final String cpf, final String reservationNumber);

    String createReservation(final CreateReservation request);

    String finalizeReservation(final CheckOutRequest request);

    String confirmReservation(final String reservationNumber);

    String cancelReservation(final String reservationNumber);
}
