package com.aiko.apiolhovivo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiko.apiolhovivo.entities.Parada;
import com.aiko.apiolhovivo.exception.BadRequestException;
import com.aiko.apiolhovivo.exception.InternalServerErrorException;
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
			boolean validCoordenades = CoordinateValidation.validation(parada.getLatitude(), parada.getLongitude());
			if(validCoordenades == false) 
				throw new BadRequestException("Coordenada(s) Inválida(s)");
			
			if(parada.getName() == null) 
				throw new BadRequestException("Nome da Parada é obrigatório");
			
			return paradarepository.save(parada);
		}catch(Exception e) {
			throw new InternalServerErrorException("Erro ao inserir, tente novamente");
		}
	}
	
	public Optional<Parada> findOneParada(Long id) {
		
		return paradarepository.findById(id);
	}
	
	public Optional<Parada> update(Parada parada) {
		
		if(parada.getLatitude() != null)
			if(CoordinateValidation.validationLatitude(parada.getLatitude())==false)
				throw new BadRequestException("Coordenada(s) inválida(s)");
		if(parada.getLongitude() != null)
			if(CoordinateValidation.validationLongitude(parada.getLongitude())==false)
				throw new BadRequestException("Coordenada(s) inválida(s)");
		
		
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
	
	public List<ParadaDistance> nextParadas(Double latitude, Double longitude, Long numberOfElements){
		if(CoordinateValidation.validation(latitude, longitude) == false)
			throw new BadRequestException("Coordenadas inválida");
		if(numberOfElements == null || numberOfElements <=0)
			numberOfElements = (long) 5;
		
		Map<Long, Double> mapOrganizer = calculateDistance(paradarepository.findAll(), latitude, longitude, numberOfElements);
		
		return createListOfParadaDistance(mapOrganizer, latitude, longitude);
	}
	
	private Map<Long, Double> calculateDistance(List<Parada> allParada, Double latitude, Double longitude, Long numberOfElements) {
				TreeMap<Long, Double> idAndDistanceParada = new TreeMap<Long, Double>();
				
				allParada.forEach(parada ->{
					Double distance = CalculateDistanceBetweenCoordinates.calculate(latitude, longitude, 
							parada.getLatitude(), parada.getLongitude());
					idAndDistanceParada.put(parada.getId(), distance);
				} );
				return idAndDistanceParada.entrySet().stream().limit(numberOfElements).
					    sorted(Entry.comparingByValue()).
					    collect(Collectors.toMap(Entry::getKey, Entry::getValue,
					                             (e1, e2) -> e1, LinkedHashMap::new));

	}
	
	private List<ParadaDistance> createListOfParadaDistance(Map<Long, Double> mapOrganizer, Double latitude, Double longitude){

		List<ParadaDistance> paradaDistance = new ArrayList<ParadaDistance>();
		
		
		for (Entry<Long, Double> item : mapOrganizer.entrySet()) {
			
			Optional<Parada> parada = paradarepository.findById(item.getKey());
	        BigDecimal distanceRoundTwoPlacesInBigDecimal = new BigDecimal(item.getValue()).setScale(2, RoundingMode.HALF_UP);
			
	        paradaDistance.add(new ParadaDistance(
					parada.get(), 
					distanceRoundTwoPlacesInBigDecimal.doubleValue()));
		}
		return paradaDistance;
	}
	
}

	

