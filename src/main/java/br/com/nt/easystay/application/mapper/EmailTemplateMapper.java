package br.com.nt.easystay.application.mapper;

import br.com.nt.easystay.application.dto.EmailTemplateDTO;
import br.com.nt.easystay.domain.model.EmailTemplate;
import br.com.nt.easystay.infrastructure.request.EmailTemplateRequest;
import br.com.nt.easystay.infrastructure.response.EmailTemplateResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmailTemplateMapper {

    private EmailTemplateMapper() {
    }

    public static EmailTemplateDTO mapRequestToDTO(EmailTemplateRequest emailTemplateRequest) {
        final EmailTemplateDTO dto = EmailTemplateDTO.builder().build();
        BeanUtils.copyProperties(emailTemplateRequest, dto);
        return dto;
    }

    public static EmailTemplateDTO mapEntityToDTO(EmailTemplate emailTemplate) {
        final EmailTemplateDTO dto = EmailTemplateDTO.builder().build();
        BeanUtils.copyProperties(emailTemplate, dto);
        return dto;
    }

    public static EmailTemplate mapDTOToEntity(EmailTemplateDTO templateDTO) {
        EmailTemplate emailTemplate = new EmailTemplate();
        BeanUtils.copyProperties(templateDTO, emailTemplate);
        return emailTemplate;
    }

    public static EmailTemplateResponse mapDTOToResponse(EmailTemplateDTO templateDTO) {
        final EmailTemplateResponse response = EmailTemplateResponse.builder().build();
        BeanUtils.copyProperties(templateDTO, response);
        return response;
    }
}
