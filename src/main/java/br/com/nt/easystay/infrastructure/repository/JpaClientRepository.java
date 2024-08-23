package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Optional<Client> findByCpfAndEmail(String cpf, String email);

}