package br.com.nt.easystay.domain.component;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.application.mapper.ClientMapper;
import br.com.nt.easystay.domain.exception.BusinessException;
import br.com.nt.easystay.domain.model.Client;
import br.com.nt.easystay.domain.repository.ClientRepository;
import br.com.nt.easystay.infrastructure.mapper.request.ClientRequestMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientComponent {

    private final ClientRepository clientRepository;

    public ClientComponent(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientDTO validateAndSaveClient(ClientDTO clientDTO) {
        final Optional<Client> client = clientRepository.findByCpf(clientDTO.getCpf());
        if (client.isPresent()) {
            return ClientMapper.toDTO(client.get());
        }
        if (clientRepository.existCpf(clientDTO.getEmail())) {
            throw new BusinessException("Email already registered", HttpStatus.CONFLICT);
        }
        Client newClient = ClientRequestMapper.toEntity(clientDTO);
        return ClientMapper.toDTO(clientRepository.save(newClient));
    }
}
