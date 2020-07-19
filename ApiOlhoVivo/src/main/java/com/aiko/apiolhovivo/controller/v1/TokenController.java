package com.aiko.apiolhovivo.controller.v1;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aiko.apiolhovivo.entities.Client;
import com.aiko.apiolhovivo.service.ClientService;
import com.aiko.apiolhovivo.service.Clienteservice;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("autenticacao/cliente")
@Api(value = "autenticação")
public class TokenController {

    @Autowired
    private ClientService customerService;
    
    @Autowired 
    private Clienteservice clienteService;
    
    @DeleteMapping()
    public ResponseEntity<?> deletClient(@RequestBody Client client){
    	
    	System.out.println(client.getSenha() + client.getUsuario());
    	Boolean status = clienteService.delete(client);
    	
    	return new ResponseEntity<>(status, HttpStatus.OK);
 	
    }

    @PostMapping("/token")
    public String getToken(Client client){
       String token= customerService.login(client.getUsuario(),client.getSenha());
       if(StringUtils.isEmpty(token)){
           return "no token found";
       }
       return token;
    }
    
    @PostMapping()
    public ResponseEntity<?> newClient(Client client){
    	
    	return new ResponseEntity<>(clienteService.create(client.getUsuario(),client.getSenha()), HttpStatus.CREATED);
    }
    
}
