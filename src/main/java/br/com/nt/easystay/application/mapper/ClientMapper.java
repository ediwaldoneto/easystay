package br.com.nt.easystay.application.mapper;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.domain.model.Client;

public class ClientMapper {

    private ClientMapper() {
    }

    public static Client toEntity(ClientDTO dto) {

        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setEmail(dto.getEmail());
        client.setPhoneNumber(dto.getPhoneNumber());
        return client;
    }
}
