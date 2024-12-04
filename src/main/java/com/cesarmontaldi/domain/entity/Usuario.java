package com.cesarmontaldi.domain.entity;

import com.cesarmontaldi.api.dto.CadastroUsuarioDTO;
import com.cesarmontaldi.api.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    @Column(unique = true)
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public void atualizarInformacoes(CadastroUsuarioDTO dto) {

        if (dto.id() != null) {
            this.id = dto.id();
        }
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
        if (dto.dataNascimento() != null) {
            this.dataNascimento = dto.dataNascimento();
        }
        if (dto.login() != null){
            this.login = dto.login();
        }
        if (dto.password() != null) {
            this.password = dto.password();
        }
    }


    public static Usuario usuarioCadastroDTOtoEntity(CadastroUsuarioDTO dto) {
        Usuario usuario = new Usuario();

        if (dto.id() != null) {
            usuario.setId(dto.id());
        }
        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());
        usuario.setPassword(dto.password());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setRole(dto.role());

        return usuario;
    }

    public static Usuario UsuarioDTOtoEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        if (dto.id() != null) {
            usuario.setId(dto.id());
        }
        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setRole(dto.role());

        return usuario;
    }

}
