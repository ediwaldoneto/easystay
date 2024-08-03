package br.com.nt.easystay.domain.repository;

import br.com.nt.easystay.domain.model.Client;

import java.util.List;

public interface ClientRepository {

    Client findById(final String id);

    void save(final Client client);

    List<Client> findAll();
}
