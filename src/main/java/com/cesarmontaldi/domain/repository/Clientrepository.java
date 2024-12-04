package com.cesarmontaldi.domain.repository;

import com.cesarmontaldi.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Clientrepository extends JpaRepository<Client, UUID> {

    Client findByClientId(String clientId);
    Boolean existsByClientId(String clientId);
}
