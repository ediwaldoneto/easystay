package br.com.nt.easystay.domain.repository;

import br.com.nt.easystay.domain.model.Client;

import java.util.List;
import java.util.Optional;


public interface ClientRepository {

    Optional<Client> findById(final String id);

    Client save(final Client client);

    List<Client> findAll();

    Optional<Client> findByCpf(String cpf);

    boolean existCpf(String cpf);

    String findClientIdByCpf(String cpf);

    boolean existEmail(String email);
}
