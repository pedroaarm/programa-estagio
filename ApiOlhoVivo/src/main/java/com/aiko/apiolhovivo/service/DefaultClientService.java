package com.aiko.apiolhovivo.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.aiko.apiolhovivo.entities.Client;
import com.aiko.apiolhovivo.repository.ClientRepository;

import java.util.Optional;
import java.util.UUID;

@Service("customerService")
public class DefaultClientService implements ClientService {

    @Autowired
    ClientRepository customerRepository;

    @Override
    public String login(String username, String password) {
        Optional<Client> customer = customerRepository.login(username,password);
        if(customer.isPresent()){
            String token = UUID.randomUUID().toString();
            Client custom= customer.get();
            custom.setToken(token);
            customerRepository.save(custom);
            return token;
        }

        return StringUtils.EMPTY;
    }

    @Override
    public Optional<User> findByToken(String token) {
        Optional<Client> customer= customerRepository.findByToken(token);
        if(customer.isPresent()){
            Client customer1 = customer.get();
            User user= new User(customer1.getUsuario(), customer1.getSenha(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
    }

    @Override
    public Client findById(Long id) {
        Optional<Client> customer= customerRepository.findById(id);
        return customer.orElse(null);
    }
}
