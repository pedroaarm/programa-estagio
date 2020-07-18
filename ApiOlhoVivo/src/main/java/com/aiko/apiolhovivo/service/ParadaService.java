package com.aiko.apiolhovivo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiko.apiolhovivo.entities.Parada;
import com.aiko.apiolhovivo.exception.BadRequestException;
import com.aiko.apiolhovivo.repository.ParadaRepository;
import com.aiko.apiolhovivo.util.CalculateDistanceBetweenCoordinates;
import com.aiko.apiolhovivo.util.CoordinateValidation;
import com.aiko.apiolhovivo.util.ParadaDistance;

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
	
	public Optional<Parada> update(Parada parada) {
		
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
	
	public List<ParadaDistance> nextParadas(Double latitude, Double longitude){
		if(CoordinateValidation.validation(latitude, longitude) == false)
			throw new BadRequestException("Coordenadas inválida");
				
		return createListOfParadaDistance(
				calculateDistance(paradarepository.findAll(), latitude, longitude),
				latitude, 
				longitude);
	}
	
	
	private List<Parada> calculateDistance(List<Parada> allParada, Double latitude, Double longitude) {
				
		return allParada
		.stream()
		.filter(record -> CalculateDistanceBetweenCoordinates.calculate(latitude, longitude, 
				record.getLatitude(), record.getLongitude())<500).collect(Collectors.toList());
			
	}
	
	private List<ParadaDistance> createListOfParadaDistance(List<Parada> paradas, Double latitude, Double longitude){

		List<ParadaDistance> paradaDistance = new ArrayList<>();
		for (Parada paradaDis : paradas) {
			double distance = CalculateDistanceBetweenCoordinates.calculate(
					latitude, 
					longitude, 
					paradaDis.getLatitude(), 
					paradaDis.getLongitude());
			
	        BigDecimal distanceRoundTwoPlacesInBigDecimal = new BigDecimal(distance).setScale(2, RoundingMode.HALF_UP);
			
	        paradaDistance.add(new ParadaDistance(
					paradaDis, 
					distanceRoundTwoPlacesInBigDecimal.doubleValue()));
		}
		return paradaDistance;
	}
	
}

	

