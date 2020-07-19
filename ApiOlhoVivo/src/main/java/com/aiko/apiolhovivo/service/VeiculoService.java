package com.aiko.apiolhovivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiko.apiolhovivo.entities.Veiculo;
import com.aiko.apiolhovivo.exception.BadRequestException;
import com.aiko.apiolhovivo.exception.NotFoundException;
import com.aiko.apiolhovivo.repository.LinhaRespository;
import com.aiko.apiolhovivo.repository.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired 
	private LinhaRespository linhaRepository;
	
	public Veiculo create(Veiculo veiculo){
		
		if(!validateVeiculo(veiculo))
			throw new BadRequestException("Insira todos os cados válidos");
		
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
		
		if(veiculo.getLinhaId()!=null && linhaRepository.existsById(veiculo.getLinhaId())==false) {
			throw new BadRequestException("ID da linha inválido: "+veiculo.getLinhaId());
		}
		return veiculoRepository.findById(veiculo.getId())
		.map(record -> {
			record.setModelo(veiculo.getModelo()==null? record.getModelo() : veiculo.getModelo());
			record.setName(veiculo.getName()==null? record.getName() : veiculo.getName());
			record.setLinhaId(veiculo.getLinhaId()==null? record.getLinhaId() : veiculo.getLinhaId());
			
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
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);
		if(veiculo.isEmpty())
			throw new NotFoundException("id Não encontrado "+id);
		
		return veiculo;
	}
	
	
	private boolean validateVeiculo(Veiculo veiculo) {
		if(veiculo.getName() == null || veiculo.getModelo() == null) 
			return false;
		else
			return linhaRepository.existsById(veiculo.getLinhaId());	
	}
	
	public List<Veiculo> veiculoPorParada(Long idLinha) {
		return veiculoRepository.findAllByLinhaId(idLinha);
	}
}
