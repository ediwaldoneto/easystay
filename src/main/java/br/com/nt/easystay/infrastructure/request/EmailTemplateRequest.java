package br.com.nt.easystay.infrastructure.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailTemplateRequest {

    private String name;
    private String subject;
    private String body;
    private String description;
    private Integer logoId;
}
