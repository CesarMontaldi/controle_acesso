package com.cesarmontaldi.domain.entity;

import com.cesarmontaldi.api.dto.CadastroUsuarioDTO;
import com.cesarmontaldi.api.dto.UsuarioDTO;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private String login;
    private String password;

    @Type(ListArrayType.class)
    @Column(columnDefinition = "varchar[]")
    private List<String> permissoes;


    public static Usuario toEntityCadastroDTO(CadastroUsuarioDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());
        usuario.setPassword(dto.password());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setPermissoes(dto.permissoes());

        return usuario;
    }

    public static Usuario toEntityUsuarioDTO(UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setPermissoes(dto.permissoes());

        return usuario;
    }

}
