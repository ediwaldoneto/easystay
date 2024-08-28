package br.com.nt.easystay.domain.service;

import br.com.nt.easystay.infrastructure.request.EmailTemplateRequest;
import br.com.nt.easystay.infrastructure.response.EmailTemplateResponse;

import java.util.List;

public interface EmailTemplateService {

    void saveTemplate(final EmailTemplateRequest emailTemplateRequest);

    List<EmailTemplateResponse> findAll();

    void deleteTemplate(final Integer id);

}
