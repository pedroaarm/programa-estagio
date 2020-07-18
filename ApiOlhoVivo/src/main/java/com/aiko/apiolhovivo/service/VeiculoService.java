package com.aiko.apiolhovivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiko.apiolhovivo.entities.Veiculo;
import com.aiko.apiolhovivo.repository.ParadaRepository;
import com.aiko.apiolhovivo.repository.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	VeiculoRepository veiculoRepository;
	
	@Autowired ParadaRepository paradaRepository;
	
	public Veiculo create(Veiculo veiculo){
		if(!validateVeiculo(veiculo))
			return null;
		
		return veiculoRepository.save(veiculo);
	}
	
	/**Recebe uma classe Veiculo e tenta fazer update a partir do ID passado
	 * O Map verifica se o atributo do atributo passado são nulos, caso seja, ele manatem o mesmo 
	 * presente no record
	 * 
	 * @param veiculo
	 * @return newVeiculo
	 */
	
	public Veiculo update(Veiculo veiculo) {
		return veiculoRepository.findById(veiculo.getId())
		.map(record -> {
			if(veiculo.getLinhaId()!=null) {
				record.setLinhaId(paradaRepository.existsById(veiculo.getLinhaId())? veiculo.getLinhaId() : record.getLinhaId());
			}
			record.setModelo(veiculo.getModelo()==null? record.getModelo() : veiculo.getModelo());
			record.setName(veiculo.getName()==null? record.getName() : veiculo.getName());
			return veiculoRepository.save(record);
		}).orElse(null);
	}
	
	public Boolean delete(Long id) {
		
		return veiculoRepository.findById(id)
				.map(recorder -> {
					veiculoRepository.delete(recorder);
					return true;
				}).orElse(false);
	}
	
	public List<Veiculo> getAll(){
		return veiculoRepository.findAll();
	}
	
	public Optional<Veiculo> selectById(Long id) {
		return veiculoRepository.findById(id);
	}
	
	
	private boolean validateVeiculo(Veiculo veiculo) {
		if(veiculo.getName().isEmpty() || veiculo.getModelo().isEmpty()) {
			return false;
		}else {
			return paradaRepository.existsById(veiculo.getLinhaId());
			
		}
	}
	
	public List<Veiculo> veiculoPorParada(Long idLinha) {
		return veiculoRepository.findAllByLinhaId(idLinha);
	}
	

}
