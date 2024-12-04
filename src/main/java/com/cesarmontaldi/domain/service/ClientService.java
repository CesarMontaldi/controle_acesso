package com.cesarmontaldi.domain.service;

import com.cesarmontaldi.api.dto.ClientDTO;
import com.cesarmontaldi.domain.entity.Client;
import com.cesarmontaldi.domain.repository.Clientrepository;
import com.cesarmontaldi.exception.RegistroDuplicadoException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final Clientrepository clientrepository;
    private final PasswordEncoder passwordEncoder;

    public Client salvar(ClientDTO dto) {
        if (clientrepository.existsByClientId(dto.clientId())) {
            throw new RegistroDuplicadoException("Client já cadastrado!");
        }
        Client client = new Client(dto);
        client.setClientSecret(passwordEncoder.encode(client.getClientSecret()));

        return clientrepository.save(client);
    }

    private Client obterPorClientID(String clientId) {
        return clientrepository.findByClientId(clientId);
    }
}
