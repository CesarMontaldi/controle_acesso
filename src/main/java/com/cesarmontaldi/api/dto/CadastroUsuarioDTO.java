package com.cesarmontaldi.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CadastroUsuarioDTO(

        @NotBlank(message = "campo obrigatorio")
        String nome,

        @NotBlank(message = "campo obrigatorio")
        String login,

        @NotBlank(message = "campo obrigatorio")
        String password,

        @NotNull(message = "campo obrigatorio")
        List<String> permissoes) {
}
