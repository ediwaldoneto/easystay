package br.com.nt.easystay.domain.repository;

import br.com.nt.easystay.domain.model.EmailTemplate;

import java.util.List;
import java.util.Optional;

public interface EmailTemplateRepository {

    void save(final EmailTemplate emailTemplate);

    void delete(final EmailTemplate emailTemplate);

    Optional<EmailTemplate> finById(final Integer id);

    Optional<EmailTemplate> findByName(final String name);

    List<EmailTemplate> findAll();
}
