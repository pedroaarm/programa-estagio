package com.aiko.apiolhovivo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiko.apiolhovivo.entities.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	List<Veiculo>findAllByLinhaId(Long linhaId);
}
