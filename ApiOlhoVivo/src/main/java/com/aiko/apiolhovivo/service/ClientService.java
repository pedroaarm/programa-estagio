package com.aiko.apiolhovivo.service;


import org.springframework.security.core.userdetails.User;

import com.aiko.apiolhovivo.entities.Client;

import java.util.Optional;

public interface ClientService {

    String login(String username, String password);
    Optional<User> findByToken(String token);
    Optional<Client> findUsuario(String usuario);
    Client findById(Long id);
}
