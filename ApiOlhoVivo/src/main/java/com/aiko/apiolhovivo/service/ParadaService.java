package com.aiko.apiolhovivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiko.apiolhovivo.entities.Parada;
import com.aiko.apiolhovivo.repository.ParadaRepository;
import com.aiko.apiolhovivo.util.CoordinateValidation;

@Service
public class ParadaService {

	@Autowired
	private ParadaRepository paradarepository;
	
	public Parada insert(Parada parada) {
		try {
			
			return paradarepository.save(parada);
		}catch(Exception e) {
			return null;
		}
	}
	
	public Optional<Parada> findOneParada(Long id) {
		
		return paradarepository.findById(id);
	}
	
	public Optional<Parada> updateParada(Parada parada) {
		
		return paradarepository.findById(parada.getId())
							   .map(record -> {
								   record.setName(parada.getName()== null ? record.getName() : parada.getName());
								   record.setLatitude(parada.getLatitude()==null? record.getLatitude() : parada.getLatitude());
								   record.setLongitude(parada.getLongitude() == null? record.getLongitude() : parada.getLongitude());
							       return paradarepository.save(record);
							       });
				
		
	}
	
	public boolean deleteParada(Long id) {
		
		return paradarepository.findById(id)
				.map(record -> {
					paradarepository.deleteById(id);
					return true;
				}).orElse(false);
	}
	
	public List<Parada> returnall(){
		return paradarepository.findAll();
	}
	
	public List<Parada> nextParadas(Double latitude, Double longitude){
		if(CoordinateValidation.validation(latitude, longitude) == false)
			throw new IllegalArgumentException("Coordenadas inválida");
		
	
		return null;
	}
	
	
}


	

