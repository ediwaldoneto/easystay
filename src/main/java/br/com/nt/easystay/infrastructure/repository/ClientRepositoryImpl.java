package br.com.nt.easystay.infrastructure.repository;

import br.com.nt.easystay.domain.model.Client;
import br.com.nt.easystay.domain.repository.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Client findById(String id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    public void save(Client client) {
        if (client.getId() == null) {
            entityManager.persist(client);
        } else {
            entityManager.merge(client);
        }
    }

    @Override
    public List<Client> findAll() {
        return entityManager
                .createQuery("from Client", Client.class)
                .getResultList();
    }
}
