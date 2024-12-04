package com.cesarmontaldi.security;

import com.cesarmontaldi.domain.entity.Usuario;
import com.cesarmontaldi.domain.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        String login = authentication.getName();
        String senha = authentication.getCredentials().toString();

        Usuario usuario = usuarioService.obterPorLogin(login);

        if (usuario == null) {
            throw getUsernameNotFoundException();
        }

        boolean senhasBatem = passwordEncoder.matches(senha, usuario.getPassword());

        if (senhasBatem) {
            return new CustomAuthentication(usuario);
        }

        throw getUsernameNotFoundException();
    }

    private UsernameNotFoundException getUsernameNotFoundException() {
        return new UsernameNotFoundException("Usuário e/ou senha incorretos!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
