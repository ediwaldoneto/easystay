package br.com.nt.easystay.infrastructure.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponse {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String cpf;
}
