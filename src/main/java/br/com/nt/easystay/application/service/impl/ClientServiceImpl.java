package br.com.nt.easystay.application.service.impl;

import br.com.nt.easystay.domain.model.Client;
import br.com.nt.easystay.domain.repository.ClientRepository;
import br.com.nt.easystay.domain.service.ClientService;
import br.com.nt.easystay.infrastructure.response.ClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ClientResponse findById(String id) {
        return null;
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<ClientResponse> findAll() {
        return List.of();
    }

    @Override
    public ClientResponse findByCpf(String cpf) {
        return null;
    }

    @Override
    public boolean existCpf(String cpf) {
        return clientRepository.existCpf(cpf);
    }

    @Override
    public String findClientIdByCpf(String cpf) {
        return clientRepository.findClientIdByCpf(cpf);
    }

    @Override
    public boolean existEmail(String email) {
        return clientRepository.existEmail(email);
    }
}
