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

import com.aiko.apiolhovivo.entities.PosicaoVeiculo;
import com.aiko.apiolhovivo.exception.NotFoundException;
import com.aiko.apiolhovivo.service.PosicaoVeiculoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/posicaoveiculo")
@Api(value = "Posição Veiculo")
public class PosicaoVeiculoController {
	
	@Autowired
	PosicaoVeiculoService posicaoVeiculoService;
	
	@PostMapping()
	@ApiOperation(value = "Adiciona uma nova PosicaoVeiculo")
	public ResponseEntity<?>NewPosicaoVeiculo(@RequestBody PosicaoVeiculo posicaoVeiculo){
	
		return new ResponseEntity<PosicaoVeiculo>(posicaoVeiculoService.insert(posicaoVeiculo), HttpStatus.CREATED);	
	}
	
	@GetMapping()
	@ApiOperation(value = "Retorna todos as Posições Cadastradas")
	public ResponseEntity<List<PosicaoVeiculo>> getAllPosicaoVeiculo(){
		
		return new ResponseEntity<List<PosicaoVeiculo>>(posicaoVeiculoService.getAll(), HttpStatus.OK);
	}
	
	@PutMapping()
	@ApiOperation(value = "Edita uma PosicaoVeiculo")
	public ResponseEntity<?>updatePosicaoVeiculo(@RequestBody PosicaoVeiculo posicaoVeiculo){
		
		return new ResponseEntity<PosicaoVeiculo>(posicaoVeiculoService.update(posicaoVeiculo), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "Retorna a PosicaoVeiculo relacionado ao id")
	public ResponseEntity<?> returnSpecificPosicaoVeiculo(@PathVariable("id") Long id){

		return new ResponseEntity<Optional<PosicaoVeiculo>>(posicaoVeiculoService.selectById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@ApiOperation(value = "Deleta uma PosicaoVeiculo pelo ID")
	public ResponseEntity<?> deletePosicaoVeiculo(@PathVariable("id") Long id){
		
		boolean result = posicaoVeiculoService.delete(id);
		if(result == false)
			throw new NotFoundException("id Não encontrado "+id);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);	
	}
}
