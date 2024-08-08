package br.com.nt.easystay.infrastructure.request;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ClientRequest {


    private String name;

    private String email;

    private String phoneNumber;

    private String cpf;
}
