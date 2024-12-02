package com.cesarmontaldi.api.controller;

import com.cesarmontaldi.api.dto.CadastroUsuarioDTO;
import com.cesarmontaldi.api.dto.UsuarioDTO;
import com.cesarmontaldi.domain.entity.Usuario;
import com.cesarmontaldi.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController implements GenericController {

    private final UsuarioService usuarioService;


    @Transactional
    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody @Valid CadastroUsuarioDTO dto) {
        Usuario usuario = usuarioService.salvar(dto);
        URI location = gerarHeaderLocation(usuario.getId());

        return ResponseEntity.created(location).body(new UsuarioDTO(usuario));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizar(@RequestBody CadastroUsuarioDTO dto) {
        Usuario usuario = usuarioService.atualizar(dto);

        return ResponseEntity.ok(new UsuarioDTO(usuario));
    }

    @Transactional
    @PutMapping("/atualizar-permissoes")
    public ResponseEntity<UsuarioDTO> atualizarPermicoes(@RequestBody CadastroUsuarioDTO dto) {
        Usuario usuario = usuarioService.atualizarPermissoes(dto);

        return ResponseEntity.ok(new UsuarioDTO(usuario));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> obterUsuario(@PathVariable UUID idUsuario) {
        Usuario usuario = usuarioService.obterUsuarioPorID(idUsuario);

        return ResponseEntity.ok(new UsuarioDTO(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @PageableDefault(page = 0, size = 5) Pageable pageable) {
        List<UsuarioDTO> listaUsuarios = usuarioService.listarUsuarios(pageable);

        return ResponseEntity.ok(listaUsuarios);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> deletar(@PathVariable UUID idUsuario) {
        usuarioService.deletarUsuario(idUsuario);

        return ResponseEntity.ok("Usuário deletado!");
    }
}
