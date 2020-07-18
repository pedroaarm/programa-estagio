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

import com.aiko.apiolhovivo.entities.PosicaoVeiculo;
import com.aiko.apiolhovivo.service.PosicaoVeiculoService;
import com.aiko.apiolhovivo.util.ErroMensage;


@RestController
@RequestMapping("api/v1/posicaoveiculo")
public class PosicaoVeiculoController {
	
	@Autowired
	PosicaoVeiculoService posicaoVeiculoService;
	
	@PostMapping("/adicionar")
	public ResponseEntity<?>createNewPosicaoVeiculo(@RequestBody PosicaoVeiculo posicaoVeiculo){
		
		PosicaoVeiculo posicaoVeiculoCreated = posicaoVeiculoService.insert(posicaoVeiculo);
		
		if(posicaoVeiculoCreated == null) {
			return new ResponseEntity<ErroMensage>(new ErroMensage("Latitude, longitude e/ou id em formado incorreto"), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<PosicaoVeiculo>(posicaoVeiculo, HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PosicaoVeiculo>> getAll(){
		
		return new ResponseEntity<List<PosicaoVeiculo>>(posicaoVeiculoService.getAll(), HttpStatus.OK);
	}
	
	
	@PutMapping("/atualizar")
	public ResponseEntity<?>updatePosicaoVeiculo(@RequestBody PosicaoVeiculo posicaoVeiculo){
		PosicaoVeiculo posicaoVeiculoUptadet = posicaoVeiculoService.update(posicaoVeiculo);
		if(posicaoVeiculoUptadet == null)
			return new ResponseEntity<ErroMensage>(new ErroMensage("Verificar os dados"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<PosicaoVeiculo>(posicaoVeiculoUptadet, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> selectById(@PathVariable("id") Long id){
		Optional<PosicaoVeiculo> result = posicaoVeiculoService.selectById(id);

		if(result.isEmpty())
			return new ResponseEntity<ErroMensage>(new ErroMensage("Id não encontrado"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Optional<PosicaoVeiculo>>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePosicaoVeiculo(@PathVariable("id") Long id){
		boolean result = posicaoVeiculoService.delete(id);
		
		if(result == false)
			return new ResponseEntity<ErroMensage>(new ErroMensage("ID não encontrado"), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
	}
	
}
