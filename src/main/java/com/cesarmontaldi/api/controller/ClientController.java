package com.cesarmontaldi.api.controller;

import com.cesarmontaldi.api.dto.ClientDTO;
import com.cesarmontaldi.domain.entity.Client;
import com.cesarmontaldi.domain.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController implements GenericController{

    private final ClientService clientService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> salvar(@RequestBody @Valid ClientDTO dto) {
        Client client = clientService.salvar(dto);
        URI location = gerarHeaderLocation(client.getId());

        return ResponseEntity.created(location).body(new ClientDTO(client));
    }
}
