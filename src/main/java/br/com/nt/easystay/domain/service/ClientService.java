package br.com.nt.easystay.domain.service;

import br.com.nt.easystay.domain.model.Client;

public interface ClientService {

    public Client findById(final Long id);

    public void save(final Client client);
}
