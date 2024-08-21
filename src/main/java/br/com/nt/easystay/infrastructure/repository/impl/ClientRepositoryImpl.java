package br.com.nt.easystay.infrastructure.repository.impl;

import br.com.nt.easystay.domain.model.Client;
import br.com.nt.easystay.domain.repository.ClientRepository;
import br.com.nt.easystay.infrastructure.repository.JpaClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final JpaClientRepository clientRepository;

    @Override
    public Optional<Client> findById(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findByCpf(String cpf) {
        return clientRepository.findByCpf(cpf);
    }

    @Override
    public boolean existCpf(String cpf) {
        return clientRepository.existsByCpf(cpf);
    }

    @Override
    public String findClientIdByCpf(String cpf) {
        return "";
    }

    @Override
    public boolean existEmail(String email) {
        return clientRepository.existsByEmail(email);
    }
}
