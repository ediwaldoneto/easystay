package br.com.nt.easystay.infrastructure.mapper.response;

import br.com.nt.easystay.domain.model.Client;
import br.com.nt.easystay.infrastructure.response.ClientResponse;

public class ClientResponseMapper {

    private ClientResponseMapper() {
    }

    public static ClientResponse toResponse(final Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .phoneNumber(client.getPhoneNumber())
                .build();
    }
}
