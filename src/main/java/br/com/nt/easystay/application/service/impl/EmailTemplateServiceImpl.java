package br.com.nt.easystay.application.service.impl;

import br.com.nt.easystay.application.dto.EmailTemplateDTO;
import br.com.nt.easystay.application.mapper.EmailTemplateMapper;
import br.com.nt.easystay.domain.component.LogoComponent;
import br.com.nt.easystay.domain.exception.EmailTemplateException;
import br.com.nt.easystay.domain.model.EmailTemplate;
import br.com.nt.easystay.domain.model.Logo;
import br.com.nt.easystay.domain.repository.EmailTemplateRepository;
import br.com.nt.easystay.domain.service.EmailTemplateService;
import br.com.nt.easystay.infrastructure.request.EmailTemplateRequest;
import br.com.nt.easystay.infrastructure.response.EmailTemplateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailTemplateServiceImpl implements EmailTemplateService {

    private final EmailTemplateRepository emailTemplateRepository;
    private final LogoComponent logoComponent;

    @Override
    public void saveTemplate(EmailTemplateRequest emailTemplateRequest) {

        final EmailTemplateDTO dto = EmailTemplateMapper.mapRequestToDTO(emailTemplateRequest);
        final EmailTemplate entity = EmailTemplateMapper.mapDTOToEntity(dto);
        final Logo logo = logoComponent.finByIdLogo(emailTemplateRequest.getLogoId());
        entity.setLogo(logo);
        emailTemplateRepository.save(entity);
    }

    @Override
    public List<EmailTemplateResponse> findAll() {
        final List<EmailTemplate> templates = emailTemplateRepository.findAll();

        return templates.stream().map(EmailTemplateMapper::mapEntityToDTO)
                .map(EmailTemplateMapper::mapDTOToResponse).toList();
    }

    @Override
    public void deleteTemplate(Integer id) {
        final EmailTemplate emailTemplate = emailTemplateRepository
                .finById(id).orElseThrow(() -> new EmailTemplateException("EmailTemplate not found with" + id));
        emailTemplateRepository.delete(emailTemplate);
    }
}