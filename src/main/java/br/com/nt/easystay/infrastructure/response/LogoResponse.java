package br.com.nt.easystay.infrastructure.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogoResponse {

    private Integer id;
    private String name;
}
