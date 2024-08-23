package br.com.nt.easystay.domain.repository;

import br.com.nt.easystay.domain.model.Logo;

import java.util.List;
import java.util.Optional;

public interface LogoRepository {

    void save(final Logo logo);

    Optional<Logo> findById(final Integer id);

    Optional<Logo> findByName(final String name);

    List<Logo> findAll();

    void delete(final Logo logo);


}
