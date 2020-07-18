package com.aiko.apiolhovivo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;



@Entity
@Table(name = "parada_linha")
public class Parada_Linha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long linha_id;
	private Long parada_id;
	
	
	
	public Parada_Linha(Long linha_id, Long parada_id) {
		super();
		this.linha_id = linha_id;
		this.parada_id = parada_id;
	}
	
	
	public Parada_Linha(Long id, Long linha_id, Long parada_id) {
		super();
		this.id = id;
		this.linha_id = linha_id;
		this.parada_id = parada_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLinha_id() {
		return linha_id;
	}
	public void setLinha_id(Long linha_id) {
		this.linha_id = linha_id;
	}
	public Long getParada_id() {
		return parada_id;
	}
	public void setParada_id(Long parada_id) {
		this.parada_id = parada_id;
	}
	
	
	

}
