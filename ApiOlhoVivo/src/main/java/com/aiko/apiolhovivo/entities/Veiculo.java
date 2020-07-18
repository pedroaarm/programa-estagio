package com.aiko.apiolhovivo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "modelo")
	private String modelo;
	
	@Column(name = "linhaid")
	private Long linhaId;
	
	

	public Veiculo() {
	}

	public Veiculo(String name, String modelo, Long linhaId) {
		super();
		this.name = name;
		this.modelo = modelo;
		this.linhaId = linhaId;
	}
	

	public Veiculo(Long id, String name, String modelo, Long linhaId) {
		super();
		this.id = id;
		this.name = name;
		this.modelo = modelo;
		this.linhaId = linhaId;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", name=" + name + ", modelo=" + modelo + ", linhaId=" + linhaId + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Long getLinhaId() {
		return linhaId;
	}

	public void setLinhaId(Long linhaId) {
		this.linhaId = linhaId;
	}
	
	
	
}
