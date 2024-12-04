package com.cesarmontaldi.domain.entity;

import com.cesarmontaldi.api.dto.ClientDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String clientId;
    private String clientSecret;
    @Column(name = "redirect_uri")
    private String redirectURI;
    private String scope;


    public Client(ClientDTO dto) {
        this.clientId = dto.clientId();
        this.clientSecret = dto.clientSecret();
        this.redirectURI = dto.redirectURI();
        this.scope = dto.scope();
    }
}
