
package com.aiko.apiolhovivo.controller.v1.parada;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiko.apiolhovivo.entities.Linha;
import com.aiko.apiolhovivo.entities.Parada;
import com.aiko.apiolhovivo.exception.NotFoundException;
import com.aiko.apiolhovivo.service.LinhaService;
import com.aiko.apiolhovivo.util.ErroMensage;
import com.aiko.apiolhovivo.util.SucessMensage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("api/v1/linha")
@Api(value = "Linha")
public class LinhaController {
	
	@Autowired
	private LinhaService linhaService;
	
	@PostMapping("")
	@ApiOperation(value = "Adiciona uma nova Linha")
	public ResponseEntity<?> newLinha(@RequestBody Linha linha){
		
		Linha createdLinha = linhaService.insertNewLinha(linha);
		
		return new ResponseEntity<Linha>(linhaService.insertNewLinha(linha), HttpStatus.CREATED);
			
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> returnSpecificLinha(@PathVariable("id") long id){
		Optional<Linha> linha = linhaService.selectLinha(id);
		if(!linha.isPresent())
			throw new NotFoundException("id - "+id);
		return new ResponseEntity<Optional<Linha>>(linha, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> returnAll(){
		
		return new ResponseEntity<List<Linha>>(linhaService.getAll(),HttpStatus.OK);
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?> updateLinha(@RequestBody Linha linha){
		System.out.println(linha.getId()+" "+linha.getName());
		Optional<Linha> linhaUptadet = linhaService.update(linha); 
		if(linhaUptadet.isEmpty())
			return new ResponseEntity<ErroMensage>(new ErroMensage("Id não encontrado"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Optional<Linha>>(linhaUptadet, HttpStatus.OK);
	
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		if(id < 0)
			return new ResponseEntity<ErroMensage>(new ErroMensage("ID inválido"), HttpStatus.BAD_REQUEST);
		
		boolean result = linhaService.delete(id);
		
		if(result == false)
			return new ResponseEntity<ErroMensage>(new ErroMensage("ID não encontrado"), HttpStatus.OK);
		else
			return new ResponseEntity<SucessMensage>(new SucessMensage("Linha Deletada"), HttpStatus.OK);
		
	}
	
	@GetMapping("linhasporparada/{idParada}")
	ResponseEntity<?> linhasPorParada(@PathVariable Long idParada){
		List<Linha> linhaPorParadaList = linhaService.getLinhasPorParada(idParada);
		return new ResponseEntity<List<Linha>>(linhaPorParadaList, HttpStatus.OK);
	}
	
	
		
	}



