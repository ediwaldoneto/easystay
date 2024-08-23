package br.com.nt.easystay.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogoDTO {

    private Integer id;
    private String name;
    private byte[] image;
}
