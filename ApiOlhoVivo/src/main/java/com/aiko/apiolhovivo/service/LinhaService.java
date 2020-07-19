package com.aiko.apiolhovivo.service;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiko.apiolhovivo.entities.Linha;
import com.aiko.apiolhovivo.entities.Parada;
import com.aiko.apiolhovivo.exception.BadRequestException;
import com.aiko.apiolhovivo.repository.LinhaRespository;
import com.aiko.apiolhovivo.repository.ParadaRepository;
import com.aiko.apiolhovivo.repository.Parada_LinhaRepository;


@Service
public class LinhaService {
	
	@Autowired
	private ParadaRepository paradaRepository;
	
	@Autowired
	private LinhaRespository linhaRepository;
	
	@Autowired
	private Parada_LinhaRepository paradaLinhaRepository;
	
	
		//Verificar pq não está retornando as paradas
	public Linha insertNewLinha (Linha newLinha) {
		if(newLinha.getParadas() !=null)
			if(VerifyIfExistParadasByID(newLinha.getParadas()) == false)
				throw new BadRequestException("Parada Inexistente");
		
		Linha newLinhaGenerated = linhaRepository.save(newLinha);
		return newLinhaGenerated;
	}
	
	private boolean VerifyIfExistParadasByID(List<Parada> paradas) {

		for (Parada parada : paradas)
			if(paradaRepository.existsById(parada.getId()) == false)
				return false;
		
		return true;
	}
	
	
	public Optional<Linha> selectLinha (Long id) {
		Optional<Linha> linha = linhaRepository.findById(id);
		return linha;
	}
	
	public  List<Linha> getAll(){
		List<Linha> linhas = linhaRepository.findAll();
		return linhas;
	}
	
	public Optional<Linha> update(Linha linha){
		try {
			if(linha.getParadas().size() > 0) {
				linha.getParadas()
				.removeIf(parada-> !paradaRepository.existsById(parada.getId()));
			}
			
		return linhaRepository.findById(linha.getId())
				.map(record -> {
					record.setName(linha.getName() == null? record.getName() : linha.getName());
					record.setParadas(linha.getParadas().isEmpty()? record.getParadas() : linha.getParadas());
					return linhaRepository.save(record);
				} );
				}catch(Exception e) {
					return null;
				}
	}
	
	public boolean delete(Long id) {
		return linhaRepository.findById(id)
				.map(record -> {
					linhaRepository.deleteById(record.getId());
					return true;
				}).orElse(false);
	}
	

	public List<Linha> getLinhasPorParada(Long idParada){
		
		List<Linha> linhasPorParada = linhaRepository.findAllById(paradaLinhaRepository.findByLinha_id(idParada));
		
		return linhasPorParada;
	}
	
	

}
