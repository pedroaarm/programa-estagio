package com.aiko.apiolhovivo.controller.v1;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

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
import com.aiko.apiolhovivo.service.VeiculoService;
import com.aiko.apiolhovivo.util.ErroMensage;
import com.aiko.apiolhovivo.util.SucessMensage;

@RestController
@RequestMapping("api/v1/veiculo")
public class VeiculoController {
	
	@Autowired
	VeiculoService veiculoService;
	
	@PostMapping("adicionar")
	public ResponseEntity<?> newVeiculo(@RequestBody Veiculo veiculo){
		
		System.out.println(veiculo.getName()+" "+veiculo.getLinhaId()+" "+veiculo.getModelo());
		
		Veiculo veiculoCreate = veiculoService.create(veiculo);
		
		if(veiculoCreate == null)
			return new ResponseEntity<ErroMensage>(new ErroMensage("Dados Invalidos"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Veiculo>(veiculoCreate, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Veiculo>> getAll(){
		return new ResponseEntity<List<Veiculo>>(veiculoService.getAll(), HttpStatus.OK);
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<?> update(@RequestBody Veiculo veiculo){
		Veiculo veiculoUpdated = veiculoService.update(veiculo);
		
		if(veiculoUpdated == null)
			return new ResponseEntity<ErroMensage>(new ErroMensage("Dados Invalidos"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Veiculo>(veiculoUpdated, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>selectById(@PathVariable Long id){
		Optional<Veiculo> veiculo = veiculoService.selectById(id);
		if(veiculo.isEmpty())
			return new ResponseEntity<ErroMensage>(new ErroMensage("Veiculo não entrado"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Optional<Veiculo>>(veiculo, HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?>delete(@PathVariable Long id){
		Boolean veiculoExcluded = veiculoService.delete(id);
		
		if(veiculoExcluded)
			return new ResponseEntity<SucessMensage>(new SucessMensage("Veiculo Excluido"), HttpStatus.OK);
		else
			return new ResponseEntity<ErroMensage>(new ErroMensage("Id não encontrado"), HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("veiculosporlinha/{idLinha}")
	public ResponseEntity<?> veiculosPorParada(@PathVariable Long idLinha){
		List<Veiculo> veiculoPorParadaList = veiculoService.veiculoPorParada(idLinha);
		
		return new ResponseEntity<List<Veiculo>>(veiculoPorParadaList, HttpStatus.OK);
		
	}
	

	

}
