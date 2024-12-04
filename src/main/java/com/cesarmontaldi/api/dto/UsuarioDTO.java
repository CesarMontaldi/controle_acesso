package com.cesarmontaldi.api.dto;

import com.cesarmontaldi.domain.entity.UserRole;
import com.cesarmontaldi.domain.entity.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UsuarioDTO(UUID id, String nome, String login, LocalDate dataNascimento, UserRole role) {

    public UsuarioDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getDataNascimento(), usuario.getRole());
    }
}
