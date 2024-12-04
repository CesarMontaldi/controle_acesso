package com.cesarmontaldi.api.dto;

import com.cesarmontaldi.domain.entity.UserRole;
import com.cesarmontaldi.domain.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CadastroUsuarioDTO(

        UUID id,

        @NotBlank(message = "campo obrigatorio")
        String nome,

        @NotBlank(message = "campo obrigatorio")
        String login,

        @NotBlank(message = "campo obrigatorio")
        String password,

        @NotNull(message = "campo obrigatorio")
        LocalDate dataNascimento,

        @NotNull(message = "campo obrigatorio")
        UserRole role) {

        public CadastroUsuarioDTO(Usuario usuario) {
                this(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getPassword(), usuario.getDataNascimento(), usuario.getRole());
        }
}
