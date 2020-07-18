package com.aiko.apiolhovivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiko.apiolhovivo.entities.PosicaoVeiculo;
import com.aiko.apiolhovivo.repository.PosicaoVeiculoRepository;
import com.aiko.apiolhovivo.repository.VeiculoRepository;
import com.aiko.apiolhovivo.util.CoordinateValidation;

@Service
public class PosicaoVeiculoService {
	
	@Autowired
	PosicaoVeiculoRepository posicaoVeiculoRepository;
	
	@Autowired
	VeiculoRepository veiculoRepository;

	public PosicaoVeiculo insert(PosicaoVeiculo posicaoVeiculo) {
		
		System.out.println(posicaoVeiculo.toString());
		
		
		if(doesItContainAllValidParameters(posicaoVeiculo) == false)
			return null;
		
		return posicaoVeiculoRepository.save(posicaoVeiculo);
	}
	
	public List<PosicaoVeiculo> getAll(){
		return posicaoVeiculoRepository.findAll();
	}
	
	public PosicaoVeiculo update (PosicaoVeiculo posicaoVeiculo) {

		if(individualValidationCoordinate(posicaoVeiculo) == false)
			return null;
		
		return posicaoVeiculoRepository.findById(posicaoVeiculo.getVeiculoId())
				.map(record -> {
					record.setLatitude(posicaoVeiculo.getLatitude() == null? record.getLatitude() : posicaoVeiculo.getLatitude());
					record.setLongitude(posicaoVeiculo.getLongitude() == null? record.getLongitude() : posicaoVeiculo.getLongitude());
					return posicaoVeiculoRepository.save(record);
				}).orElse(null);
	}
	
	public boolean delete(Long id) {
		return posicaoVeiculoRepository.findById(id)
				.map(record -> {
					posicaoVeiculoRepository.deleteById(id);
					return true;
				}).orElse(false);
	}
	
	public Optional<PosicaoVeiculo> selectById(Long id) {
		return posicaoVeiculoRepository.findById(id);
	}
	
	private boolean doesItContainAllValidParameters(PosicaoVeiculo posicaoVeiculo) {
		if(posicaoVeiculo.getLatitude() == null
				|| posicaoVeiculo.getLongitude() == null
				|| posicaoVeiculo.getVeiculoId() == null)
			return false;
		
		if(CoordinateValidation.validation(posicaoVeiculo.getLatitude(), posicaoVeiculo.getLongitude()) == false)
			return false;
		
		if(veiculoRepository.existsById(posicaoVeiculo.getVeiculoId()) == false)
			return false;
					
		return true;
	}
	
	
	private boolean individualValidationCoordinate(PosicaoVeiculo posicaoVeiculo) {
		if(posicaoVeiculo.getLatitude() != null) 
			if(CoordinateValidation.validationLatitude(posicaoVeiculo.getLatitude()) == false)
				return false;
		
		if(posicaoVeiculo.getLongitude() != null) 
			if(CoordinateValidation.validationLongitude(posicaoVeiculo.getLongitude()) == false)
				return false;
		
		return true;
			
	}
	
			
			
	}

