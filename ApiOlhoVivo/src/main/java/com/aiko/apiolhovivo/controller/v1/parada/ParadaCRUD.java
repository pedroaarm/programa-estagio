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
import com.aiko.apiolhovivo.entities.Parada;
import com.aiko.apiolhovivo.service.ParadaService;
import com.aiko.apiolhovivo.util.CoordinateValidation;
import com.aiko.apiolhovivo.util.ErroMensage;
import com.aiko.apiolhovivo.util.SucessMensage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/parada")
@Api(value = "parada")
public class ParadaCRUD {
	
	@Autowired
	private ParadaService paradaservice;
	

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna a parada relacionado ao id")
	public ResponseEntity<?> returnSpecifcParada(@PathVariable("id") long id){

		if(id < 0)
			return new ResponseEntity<ErroMensage>(new ErroMensage("ID inválido"), HttpStatus.BAD_REQUEST);	
		Optional<Parada> parada = paradaservice.findOneParada(id);

		if(parada.isEmpty())
			return new ResponseEntity<ErroMensage>(new ErroMensage("ID não encontrado"), HttpStatus.OK);	
		else
			return new ResponseEntity<Optional<Parada>>(parada,HttpStatus.OK);
	}
		
	@PostMapping("adicionar")
	public ResponseEntity<?> newParada(@RequestBody Parada parada){
		
		boolean validCoordenades = CoordinateValidation.validation(parada.getLatitude(), parada.getLongitude());
		if(validCoordenades == false)
			return new ResponseEntity<ErroMensage>(new ErroMensage("Latitude e/ou longitude em formado incorreto"), HttpStatus.BAD_REQUEST);

		Parada newParadaCreated = paradaservice.insert(parada);

		return new ResponseEntity<Parada>(newParadaCreated, HttpStatus.OK);
	}
	
	@PutMapping("editar")
	public ResponseEntity<?> updateParada(@RequestBody Parada parada){
		
		if(parada.getId() == 0)
			return new ResponseEntity<ErroMensage>(new ErroMensage("ID é obrigatório"), HttpStatus.BAD_REQUEST);
		
		Optional<Parada> para = paradaservice.updateParada(parada);
		return new ResponseEntity<Optional<Parada>>(para, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteParada(@PathVariable("id") long id){
		
		if(id < 0)
			return new ResponseEntity<ErroMensage>(new ErroMensage("ID inválido"), HttpStatus.BAD_REQUEST);
		
		boolean result = paradaservice.deleteParada(id);
		
		if(result == false)
			return new ResponseEntity<ErroMensage>(new ErroMensage("ID não encontrado"), HttpStatus.OK);
		else
			return new ResponseEntity<SucessMensage>(new SucessMensage("Parada Deletada"), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllParada(){
		
		return new ResponseEntity<List<Parada>>(paradaservice.returnall(), HttpStatus.OK);
	}
	
	/**
	@GetMapping("paradasproximas/{latitude}/{longitude}")
	public ResponseEntity<?> returnnew (@PathVariable("latitude") Long latitude, @PathVariable("longitude") Long longitude){
			List<Paradas> nextParadas = paradaservice.nextParadas(latitude, longitude);
			return null;
	}
	**/

}
