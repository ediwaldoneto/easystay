package br.com.nt.easystay.domain.component;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.domain.exception.BusinessException;
import br.com.nt.easystay.domain.service.ClientService;
import br.com.nt.easystay.infrastructure.mapper.request.ClientRequestMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ClientComponent {

    private final ClientService clientService;

    public ClientComponent(ClientService clientService) {
        this.clientService = clientService;
    }

    @Transactional
    public String validateAndSaveClient(ClientDTO clientDTO) {

        final String cpf = clientService.findClientIdByCpf(clientDTO.getCpf());
        if (cpf != null) {
            return cpf;
        }

        if (clientService.existCpf(clientDTO.getEmail())) {
            throw new BusinessException("Email already registered", HttpStatus.CONFLICT);
        }
        return clientService.save(ClientRequestMapper.toEntity(clientDTO));
    }
}
