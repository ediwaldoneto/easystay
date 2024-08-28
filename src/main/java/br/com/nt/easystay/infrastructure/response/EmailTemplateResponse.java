package br.com.nt.easystay.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailTemplateResponse {

    private Integer id;
    private String name;
    private String subject;
    private String description;
    private Integer logoId;
}
