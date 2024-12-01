package com.cesarmontaldi.domain.service;

import com.cesarmontaldi.api.dto.CadastroUsuarioDTO;
import com.cesarmontaldi.domain.entity.Usuario;
import com.cesarmontaldi.domain.repository.UsuarioRepository;
import com.cesarmontaldi.exception.RegistroDuplicadoException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvar(CadastroUsuarioDTO dto) {
        if (usuarioRepository.existsByLogin(dto.login())) {
            throw new RegistroDuplicadoException("Usuário já cadastrado.");
        }
        var usuario = Usuario.toEntityCadastroDTO(dto);

        return usuarioRepository.save(usuario);
    }

    public Usuario obterUsuarioPorID(UUID idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
    }


}
