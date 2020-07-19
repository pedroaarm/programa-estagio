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

import com.aiko.apiolhovivo.entities.Veiculo;
import com.aiko.apiolhovivo.exception.NotFoundException;
import com.aiko.apiolhovivo.service.VeiculoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/veiculo")
@Api(value = "veiculo")
public class VeiculoController {
	
	@Autowired
	VeiculoService veiculoService;
	
	@PostMapping()
	@ApiOperation(value = "Adiciona um novo Veiculo")
	public ResponseEntity<?> newVeiculo(@RequestBody Veiculo veiculo){
		
		return new ResponseEntity<Veiculo>(veiculoService.create(veiculo), HttpStatus.OK);
	}
	
	@GetMapping()
	@ApiOperation(value = "Retorna todos os Veiculos cadastrados")
	public ResponseEntity<List<Veiculo>> getAllVeiculo(){
		
		return new ResponseEntity<List<Veiculo>>(veiculoService.getAll(), HttpStatus.OK);
	}
	
	@PutMapping()
	@ApiOperation(value = "Edita um veiculo")
	public ResponseEntity<?> updateVeiculo(@RequestBody Veiculo veiculo){
		
		Veiculo veiculoUpdated = veiculoService.update(veiculo);
		if(veiculoUpdated == null)
			throw new NotFoundException("Dados invalidos");
		
		return new ResponseEntity<Veiculo>(veiculoUpdated, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "Retorna um relacionado ao id")
	public ResponseEntity<?>selectById(@PathVariable Long id){
		
		return new ResponseEntity<Optional<Veiculo>>(veiculoService.selectById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@ApiOperation(value = "Deleta uma Veiculo pelo ID")
	public ResponseEntity<?>delete(@PathVariable Long id){
		
		Boolean veiculoExcluded = veiculoService.delete(id);
		if(veiculoExcluded)
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		else
			throw new NotFoundException("id Não encontrado "+id);
	}
	
	@GetMapping("linha/{idLinha}")
	@ApiOperation(value = "Dado o ID de uma Linha, retorna todas os veiculos relacionadas a linha")
	public ResponseEntity<?> veiculosPorParada(@PathVariable Long idLinha){

		return new ResponseEntity<List<Veiculo>>(veiculoService.veiculoPorParada(idLinha), HttpStatus.OK);
	}
}
