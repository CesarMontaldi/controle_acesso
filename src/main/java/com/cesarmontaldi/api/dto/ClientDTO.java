package com.cesarmontaldi.api.dto;

import com.cesarmontaldi.domain.entity.Client;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record ClientDTO(
        @NotBlank(message = "campo obrigatorio")
        String clientId,
        @NotBlank(message = "campo obrigatorio")
        String clientSecret,
        @NotBlank(message = "campo obrigatorio")
        String redirectURI,
        @NotBlank(message = "campo obrigatorio")
        String scope) {

    public ClientDTO(Client client) {
        this(client.getClientId(), client.getClientSecret(), client.getRedirectURI(), client.getScope());
    }
}
