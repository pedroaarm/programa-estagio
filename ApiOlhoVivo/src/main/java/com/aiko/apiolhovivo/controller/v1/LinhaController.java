
package com.aiko.apiolhovivo.controller.v1;

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
import com.aiko.apiolhovivo.exception.BadRequestException;
import com.aiko.apiolhovivo.exception.NotFoundException;
import com.aiko.apiolhovivo.service.LinhaService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("api/v1/linha")
@Api(value = "Linha")
public class LinhaController {
	
	@Autowired
	private LinhaService linhaService;
	
	@PostMapping()
	@ApiOperation(value = "Adiciona uma nova Linha")
	public ResponseEntity<?> newLinha(@RequestBody Linha linha){
		
		if(linha.getName() ==null)
			throw new BadRequestException("Campo nome é obrigatório");
			
		Linha createdLinha = linhaService.insertNewLinha(linha);
		
		if(createdLinha == null)
			throw new InternalError("Erro interno");
		else
		return new ResponseEntity<>(linhaService.selectLinha(createdLinha.getId()),HttpStatus.CREATED);		
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca uma linha a partir do id informado")
	public ResponseEntity<?> returnSpecificLinha(@PathVariable("id") long id){
		Optional<Linha> linha = linhaService.selectLinha(id);
		if(!linha.isPresent())
			throw new NotFoundException("id Não encontrado "+id);
		
		return new ResponseEntity<Optional<Linha>>(linha, HttpStatus.OK);
	}
	
	@GetMapping()
	@ApiOperation(value = "Rotorna todas as Linhas cadastradas")
	public ResponseEntity<?> returnAllLinhas(){
		List<Linha> linhas = linhaService.getAll();

		return new ResponseEntity<List<Linha>>(linhas,HttpStatus.OK);
	}
	
	@PutMapping()
	@ApiOperation(value = "Edita uma linha")
	public ResponseEntity<?> updateLinha(@RequestBody Linha linha){

		if(linha.getId() == null) 
			throw new BadRequestException("ID é obrigatório");
		if(linha.getName() == "")
			throw new BadRequestException("Campo nome não pode ser vazio");
			
		Optional<Linha> linhaUptadet = linhaService.update(linha); 
		
		if(linhaUptadet.isEmpty()) 
			throw new NotFoundException("id Não encontrado "+linha.getId());
			
		return new ResponseEntity<Optional<Linha>>(linhaUptadet, HttpStatus.OK);
	
	}
	
	@DeleteMapping("{id}")
	@ApiOperation(value = "Deleta uma linha pelo id")
	public ResponseEntity<?> deleteLinha(@PathVariable Long id){
		
		boolean result = linhaService.delete(id);
		
		if(result == false)
			throw new NotFoundException("id Não encontrado "+id);
		else
			return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@GetMapping("parada/{idParada}")
	@ApiOperation(value = "Retorna todas as linhas relacionadas a determinada parada")
	ResponseEntity<?> linhasPorParada(@PathVariable Long idParada){
		
		return new ResponseEntity<List<Linha>>(linhaService.getLinhasPorParada(idParada), HttpStatus.OK);
	}	

}



