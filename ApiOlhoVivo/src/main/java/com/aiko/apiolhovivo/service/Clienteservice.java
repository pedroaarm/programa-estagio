package com.aiko.apiolhovivo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiko.apiolhovivo.entities.Client;
import com.aiko.apiolhovivo.exception.BadRequestException;
import com.aiko.apiolhovivo.repository.ClientRepository;



@Service
public class Clienteservice {
	
	@Autowired
	ClientRepository clienteRepository;
	
	public Client create(String username, String password) {
		Client client = clienteRepository.save(new Client (username,password));
		return client;
	}
	
	public boolean delete(Client client) {
		System.err.println(client.getUsuario());

			if(validateCliente(client) == false)
				throw new BadRequestException("usuario e Senha são obrigatórios");
			
		Client clientToDelete = clienteRepository.findByUsuario(client.getUsuario());
		System.err.println(clientToDelete.getUsuario()+"------");
		clienteRepository.delete(clientToDelete);
		return true;
		
	}
	
	private boolean validateCliente(Client client) {
		if(client.getSenha() == null) 
			return false;
		
		if(client.getUsuario() == null)
			return false;
		
		return true;
	}
	

}
