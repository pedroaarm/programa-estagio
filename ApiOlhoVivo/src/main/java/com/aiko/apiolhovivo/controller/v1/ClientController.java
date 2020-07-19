package com.aiko.apiolhovivo.controller.v1;



import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aiko.apiolhovivo.entities.Client;
import com.aiko.apiolhovivo.security.TokenClass;
import com.aiko.apiolhovivo.service.Clienteservice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("autenticacao/cliente")
@Api(value = "autenticação e token")
public class ClientController {

    
    @Autowired 
    private Clienteservice clienteService;
    
    @PostMapping()
    @ApiOperation(value = "Cria um novo cliente")
    public ResponseEntity<?> newClient(@RequestBody Client client) throws SQLIntegrityConstraintViolationException{
    	
    	return new ResponseEntity<>(clienteService.create(client), HttpStatus.CREATED);
    }
    
    @DeleteMapping()
    @ApiOperation(value = "Deleta um cliente")
    public ResponseEntity<?> deletClient(@RequestBody Client client){
    	
    	Boolean status = clienteService.delete(client);
    	return new ResponseEntity<>(status, HttpStatus.OK);
    }
    

    
    @PostMapping("/token")
    @ApiOperation(value = "Gera um novo token para o cliente")
    public ResponseEntity<?> setToken(@RequestBody Client client){

       return new ResponseEntity<TokenClass>(clienteService.login(client.getUsuario(),client.getSenha()), HttpStatus.OK);
    }
    
    @GetMapping("/token")
    @ApiOperation(value = "Retorna o token que faz referencia ao cliente")
    public ResponseEntity<?>getToken(@RequestBody Client client){

    	return new ResponseEntity<TokenClass>(clienteService.getToken(client), HttpStatus.OK);
    }
}
