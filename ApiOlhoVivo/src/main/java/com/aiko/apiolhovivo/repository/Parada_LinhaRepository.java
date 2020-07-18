package com.aiko.apiolhovivo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aiko.apiolhovivo.entities.Parada_Linha;

@Repository
public interface Parada_LinhaRepository extends JpaRepository<Parada_Linha, Long> {
	

    @Query("select linha_id from Parada_Linha where parada_id = :id")
    List<Long> findByLinha_id(@Param("id") Long id);


}
