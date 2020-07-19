package com.aiko.apiolhovivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aiko.apiolhovivo.entities.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT u FROM Client u where u.usuario = ?1 and u.senha = ?2 ")
    Optional<Client> login(String username,String password);    
    @Query(value = "Select u FROM Client u where u.usuario = ?1")
    Optional<Client> findUsuario(String usuario);
    Optional<Client> findByToken(String token);
    
    @Query(value="select u.token FROM Client u where u.usuario = ?1 and u.senha= ?2")
    String getToken(String usuario, String senha);
}
