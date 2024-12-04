package com.cesarmontaldi.security;

import com.cesarmontaldi.domain.entity.UserRole;
import com.cesarmontaldi.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class CustomAuthentication implements Authentication {

    private final Usuario usuario;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (usuario.getRole() == UserRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority(UserRole.ADMIN.getRole()),
                    new SimpleGrantedAuthority(UserRole.USER.getRole()));
        } else {
            return List.of(new SimpleGrantedAuthority(UserRole.USER.getRole()));
        }
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return usuario;
    }

    @Override
    public Object getPrincipal() {
        return usuario;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return usuario.getNome();
    }
}
