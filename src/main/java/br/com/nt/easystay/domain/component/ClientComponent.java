package br.com.nt.easystay.domain.component;

import br.com.nt.easystay.application.dto.ClientDTO;
import br.com.nt.easystay.domain.exception.BusinessException;
import br.com.nt.easystay.domain.service.ClientService;
import br.com.nt.easystay.infrastructure.mapper.request.ClientRequestMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientComponent {

    private final ClientService clientService;

    public ClientComponent(ClientService clientService) {
        this.clientService = clientService;
    }

    public String validateAndSaveClient(ClientDTO clientDTO) {

        if (clientService.exist(clientDTO.getCpf())) {
            throw new BusinessException("CPF already registered");
        }
        return clientService.save(ClientRequestMapper.toEntity(clientDTO));
    }
}
