package com.cesarmontaldi.api.dto;

import java.util.List;
import java.util.UUID;

public record UsuarioDTO(UUID id, String nome, String login, List<String> permissoes) {
}
