package br.com.nt.easystay.domain.service;

import br.com.nt.easystay.domain.model.Reservation;
import br.com.nt.easystay.infrastructure.response.ReservationResponse;
import br.com.nt.easystay.infrastructure.request.ReservationRequest;

import java.util.List;

public interface ReservationService {

    ReservationResponse findById(final Long id);

    void save(final Reservation reservation);

    List<ReservationResponse> findAll();

    void delete(final Long id);

    void update(final ReservationRequest request);

    void createReservation(ReservationRequest request);
}
