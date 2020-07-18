package com.aiko.apiolhovivo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiko.apiolhovivo.entities.Parada;

@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long> {
}
