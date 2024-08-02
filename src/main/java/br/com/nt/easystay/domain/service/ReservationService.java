package br.com.nt.easystay.domain.service;

import br.com.nt.easystay.domain.repository.ReservationRepository;

public interface ReservationService {

    public ReservationRepository findById(final Long id);

    public void save(final ReservationRepository reservation);
}
