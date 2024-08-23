package br.com.nt.easystay.infrastructure.repository.impl;

import br.com.nt.easystay.domain.model.Logo;
import br.com.nt.easystay.domain.repository.LogoRepository;
import br.com.nt.easystay.infrastructure.repository.JpaLogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LogoRepositoryImpl implements LogoRepository {

    private final JpaLogoRepository jpaLogoRepository;

    @Override
    public void save(Logo logo) {
        jpaLogoRepository.save(logo);
    }

    @Override
    public Optional<Logo> findById(Integer id) {
        return jpaLogoRepository.findById(id);
    }

    @Override
    public Optional<Logo> findByName(String name) {
        return jpaLogoRepository.findByName(name);
    }

    @Override
    public List<Logo> findAll() {
        return jpaLogoRepository.findAll();
    }

    @Override
    public void delete(Logo logo) {
        jpaLogoRepository.delete(logo);
    }


}
