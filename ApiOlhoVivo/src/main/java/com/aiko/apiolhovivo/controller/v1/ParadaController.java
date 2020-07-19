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
import com.aiko.apiolhovivo.entities.Parada;
import com.aiko.apiolhovivo.exception.BadRequestException;
import com.aiko.apiolhovivo.exception.InternalServerErrorException;
import com.aiko.apiolhovivo.exception.NotFoundException;
import com.aiko.apiolhovivo.service.ParadaService;
import com.aiko.apiolhovivo.util.CoordinateValidation;
import com.aiko.apiolhovivo.util.ParadaDistance;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/parada")
@Api(value = "parada")
public class ParadaController {
	
	@Autowired
	private ParadaService paradaservice;
	

	@PostMapping()
	@ApiOperation(value = "Adiciona uma nova Parada")
	public ResponseEntity<?> newParada(@RequestBody Parada parada){
		
		boolean validCoordenades = CoordinateValidation.validation(parada.getLatitude(), parada.getLongitude());
		if(validCoordenades == false) 
			throw new BadRequestException("Coordenada(s) Inválida(s)");
		
		if(parada.getName() == null) 
			throw new BadRequestException("Nome da Parada é obrigatório");
		
		Parada newParadaCreated = paradaservice.insert(parada);
		
		if(newParadaCreated == null) 
			throw new InternalServerErrorException("Erro ao inserir, tente novamente");
		
		return new ResponseEntity<Parada>(newParadaCreated, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna a parada relacionado ao id")
	public ResponseEntity<?> returnSpecifcParada(@PathVariable("id") long id){


		Optional<Parada> parada = paradaservice.findOneParada(id);

		if(!parada.isPresent())
			throw new NotFoundException("id Não encontrado "+id);	
		
		return new ResponseEntity<Optional<Parada>>(parada,HttpStatus.OK);
	}
		

	
	@PutMapping()
	@ApiOperation(value = "Edita uma parada")
	public ResponseEntity<?> updateParada(@RequestBody Parada parada){
		
		Optional<Parada> paradaEdited = paradaservice.update(parada);
		if(!paradaEdited.isPresent())
			throw new NotFoundException("id Não encontrado "+parada.getId());	
		
		return new ResponseEntity<Optional<Parada>>(paradaEdited, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@ApiOperation(value = "Deleta uma parada pelo ID")
	public ResponseEntity<?> deleteParada(@PathVariable("id") long id){

		boolean result = paradaservice.deleteParada(id);
		
		if(result == false)
			throw new NotFoundException("id Não encontrado "+id);	
		else
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@GetMapping()
	@ApiOperation(value = "Retorna todos as paradas cadastradas")
	public ResponseEntity<?> getAllParada(){
		
		return new ResponseEntity<List<Parada>>(paradaservice.returnall(), HttpStatus.OK);
	}
	
	@GetMapping("paradasproximas/{latitude}/{longitude}")
	public ResponseEntity<?> returnnew (@PathVariable("latitude") Double latitude, @PathVariable("longitude") Double longitude){
			List<ParadaDistance> nextParadas = paradaservice.nextParadas(latitude, longitude);
			return new ResponseEntity<List<ParadaDistance>>(nextParadas, HttpStatus.OK);
	}
}
