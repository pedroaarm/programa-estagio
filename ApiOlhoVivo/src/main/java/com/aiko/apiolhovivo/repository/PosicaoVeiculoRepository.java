package com.aiko.apiolhovivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiko.apiolhovivo.entities.PosicaoVeiculo;

@Repository
public interface PosicaoVeiculoRepository extends JpaRepository<PosicaoVeiculo, Long> {

}
