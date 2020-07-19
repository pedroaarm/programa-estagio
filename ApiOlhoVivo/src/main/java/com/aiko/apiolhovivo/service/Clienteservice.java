package com.aiko.apiolhovivo.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiko.apiolhovivo.entities.Client;
import com.aiko.apiolhovivo.exception.BadRequestException;
import com.aiko.apiolhovivo.repository.ClientRepository;
import com.aiko.apiolhovivo.security.TokenClass;
import com.aiko.apiolhovivo.util.SucessMensage;

/**Classe Service para Client, é chamada no controller para valida e fazer as operações de acordo com que for chamado
 * 
 * @author pedro
 *
 */

@Service
public class Clienteservice {
	
	@Autowired
	private ClientRepository clienteRepository;
	
    @Autowired
    private ClientService customerService;
    
    public TokenClass login(String usuario, String senha) {
    	if(usuario == null || senha == null)
    		throw new BadRequestException("Usuário e Senha são obrigatórios");
    	
    	String token = customerService.login(usuario, senha);
    	 if(StringUtils.isEmpty(token))
    		 throw new BadRequestException("Usuario e/ou senha invalido(s)");
    		 
    	return new TokenClass(token); 
    	
    }
    
    public TokenClass getToken(Client client) {
    	if(client.getUsuario() == null || client.getSenha() == null)
    		throw new BadRequestException("Usuário e Senha são obrigatórios");
    	String token = clienteRepository.getToken(client.getUsuario(), client.getSenha());
   	 if(StringUtils.isEmpty(token))
		 throw new BadRequestException("Usuario e/ou senha invalido(s)");

    	return new TokenClass(token);
    }
	
	public SucessMensage create(Client client) throws SQLIntegrityConstraintViolationException {
		try {
		if(validateCliente(client) == false) {
			throw new BadRequestException("usuario e Senha são obrigatórios");
		}
		clienteRepository.save(new Client (client.getUsuario(),client.getSenha()));
		
		return new SucessMensage("Usuario Criado");
		}catch(Exception e) {
			throw new SQLIntegrityConstraintViolationException();
		}
	}
	
	public boolean delete(Client client) {

		if(validateCliente(client) == false)
			throw new BadRequestException("usuario e Senha são obrigatórios");
			
		Optional<Client> clientToDelete = clienteRepository.findUsuario(client.getUsuario());
		
		if(!clientToDelete.isPresent())
			throw new BadRequestException("usuario e/ou senha incorretos");
		
		clienteRepository.delete(clientToDelete.get());
		return true;
	}
	

	public List<Client> returnAll(){
		return clienteRepository.findAll();
	}
	
	private boolean validateCliente(Client client) {
		if(client.getSenha() == null) 
			return false;
		
		if(client.getUsuario() == null)
			return false;
		
		return true;
	}
}
