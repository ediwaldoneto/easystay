package br.com.nt.easystay.infrastructure.mapper.request;


import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.domain.model.Client;
import br.com.nt.easystay.infrastructure.request.ClientRequest;

public class ClientRequestMapper {

    private ClientRequestMapper() {
    }

    public static ClientDTO toDTO(ClientRequest request) {

       return ClientDTO.builder()
               .name(request.getName())
               .email(request.getEmail())
               .phoneNumber(request.getPhoneNumber())
               .cpf(request.getCpf())
               .build();
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
