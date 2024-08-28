package br.com.nt.easystay.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailTemplateDTO {

    private Integer id;
    private String name;
    private String subject;
    private String body;
    private String description;
    private Integer logoId;
}
