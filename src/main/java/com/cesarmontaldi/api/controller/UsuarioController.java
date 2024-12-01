package com.cesarmontaldi.api.controller;

import com.cesarmontaldi.api.dto.CadastroUsuarioDTO;
import com.cesarmontaldi.api.dto.UsuarioDTO;
import com.cesarmontaldi.domain.entity.Usuario;
import com.cesarmontaldi.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController implements GenericController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody CadastroUsuarioDTO dto) {
        Usuario usuario = usuarioService.salvar(dto);
        URI location = gerarHeaderLocation(usuario.getId());

        return ResponseEntity.created(location).body(new UsuarioDTO(usuario));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obterUsuario(@PathVariable UUID idUsuario) {
        Usuario usuario = usuarioService.obterUsuarioPorID(idUsuario);

        return ResponseEntity.ok(new UsuarioDTO(usuario));
    }
}
