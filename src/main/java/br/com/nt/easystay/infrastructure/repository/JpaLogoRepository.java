package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.model.Logo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaLogoRepository extends JpaRepository<Logo, Integer> {

    Optional<Logo> findByName(String name);

}
