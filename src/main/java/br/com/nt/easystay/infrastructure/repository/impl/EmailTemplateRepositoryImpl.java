package br.com.nt.easystay.infrastructure.repository.impl;

import br.com.nt.easystay.domain.model.EmailTemplate;
import br.com.nt.easystay.domain.repository.EmailTemplateRepository;
import br.com.nt.easystay.infrastructure.repository.JpaEmailTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailTemplateRepositoryImpl implements EmailTemplateRepository {

    private final JpaEmailTemplateRepository jpaEmailTemplateRepository;

    @Override
    public void save(EmailTemplate emailTemplate) {
        jpaEmailTemplateRepository.save(emailTemplate);
    }

    @Override
    public void delete(EmailTemplate emailTemplate) {
        jpaEmailTemplateRepository.delete(emailTemplate);
    }

    @Override
    public Optional<EmailTemplate> finById(Integer id) {
        return jpaEmailTemplateRepository.findById(id);
    }

    @Override
    public Optional<EmailTemplate> findByName(String name) {
        return jpaEmailTemplateRepository.findByName(name);
    }

    @Override
    public List<EmailTemplate> findAll() {
        return jpaEmailTemplateRepository.findAll();
    }
}
