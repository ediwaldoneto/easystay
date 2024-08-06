package br.com.nt.easystay.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDTO {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String cpf;
}
