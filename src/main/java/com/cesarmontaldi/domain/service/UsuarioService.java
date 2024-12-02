package com.cesarmontaldi.domain.service;

import com.cesarmontaldi.api.dto.CadastroUsuarioDTO;
import com.cesarmontaldi.api.dto.UsuarioDTO;
import com.cesarmontaldi.domain.entity.Usuario;
import com.cesarmontaldi.domain.repository.UsuarioRepository;
import com.cesarmontaldi.exception.RegistroDuplicadoException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario salvar(CadastroUsuarioDTO dto) {
        if (usuarioRepository.existsByLogin(dto.login())) {
            throw new RegistroDuplicadoException("Usuário já cadastrado.");
        }
        var usuario = Usuario.usuarioCadastroDTOtoEntity(dto);

        return usuarioRepository.save(usuario);
    }


    public Usuario atualizar(CadastroUsuarioDTO dto) {
        Usuario usuario = usuarioRepository.getReferenceById(dto.id());

        usuario.atualizarInformacoes(dto);

        return usuario;
    }

    public Usuario atualizarPermissoes(CadastroUsuarioDTO dto) {
        Usuario usuario = usuarioRepository.getReferenceById(dto.id());

        dto.permissoes()
                .forEach(perm -> {
                    if (!usuario.getPermissoes().contains(perm)){
                        usuario.getPermissoes().add(perm);
                    }
                });

        return usuario;
    }

    public Usuario obterUsuarioPorID(UUID idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
    }

    public List<UsuarioDTO> listarUsuarios(Pageable paginacao) {
        return usuarioRepository.findAll(paginacao)
                .stream()
                .map(UsuarioDTO::new)
                .toList();
    }

    public void deletarUsuario(UUID idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        usuarioRepository.delete(usuario);
    }

}
