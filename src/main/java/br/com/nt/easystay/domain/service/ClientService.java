package br.com.nt.easystay.domain.service;

import br.com.nt.easystay.domain.model.Client;
import br.com.nt.easystay.infrastructure.response.ClientResponse;

import java.util.List;

public interface ClientService {

    ClientResponse findById(final String id);

    Client save(final Client client);

    List<ClientResponse> findAll();

    ClientResponse findByCpf(final String cpf);

    boolean existCpf(final String cpf);

    String findClientIdByCpf(String cpf);

    boolean existEmail(final String email);
}
