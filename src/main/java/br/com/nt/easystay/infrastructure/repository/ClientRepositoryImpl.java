package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.exception.ClientNotFoundException;
import br.com.nt.easystay.domain.model.Client;
import br.com.nt.easystay.domain.repository.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Client findById(String id) {
        final Client client = entityManager.find(Client.class, id);
        if (client != null) {
            return client;
        } else {
            throw new ClientNotFoundException("Client not found with id " + id);
        }
    }

    @Override
    public String save(Client client) {
        if (client.getId() == null) {
            client.setId(generateHashId());
            entityManager.persist(client);
        } else {
            entityManager.merge(client);
        }
        return client.getId();
    }

    @Override
    public List<Client> findAll() {
        return entityManager
                .createQuery("from Client", Client.class)
                .getResultList();
    }

    @Override
    public Client findByCpf(String cpf) {
        TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c WHERE c.cpf :cpf", Client.class);
        query.setParameter("cpf", cpf);
        final Client client = query.getSingleResult();
        if (client != null) {
            return client;
        } else {
            throw new ClientNotFoundException("Client not found with cpf " + cpf);
        }
    }

    @Override
    public boolean exist(String cpf) {
        Query query = entityManager.createQuery("SELECT COUNT(c) FROM Client c WHERE c.cpf = :cpf");
        query.setParameter("cpf", cpf);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

    private String generateHashId() {
        return UUID.randomUUID().toString();
    }
}
