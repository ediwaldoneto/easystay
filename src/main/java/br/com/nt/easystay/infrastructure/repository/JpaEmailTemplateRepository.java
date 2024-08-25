package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.model.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaEmailTemplateRepository extends JpaRepository<EmailTemplate, Integer> {

    Optional<EmailTemplate> findByName(String name);
}
