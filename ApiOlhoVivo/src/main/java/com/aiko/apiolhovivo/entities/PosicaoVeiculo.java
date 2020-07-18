package com.aiko.apiolhovivo.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "posicaoVeiculo")
public class PosicaoVeiculo {
	
	@Id
	private Long veiculoId;
	
	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "longitude")
	private Double longitude;
	
	
	

	public PosicaoVeiculo(Long veiculoId, Double latitude, Double longitude) {
		super();
		this.veiculoId = veiculoId;
		this.latitude = latitude;
		this.longitude = longitude;
	}



	public PosicaoVeiculo() {

	}

	

	@Override
	public String toString() {
		return "PosicaoVeiculo [veiculoId=" + veiculoId + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}



	public Long getVeiculoId() {
		return veiculoId;
	}



	public void setVeiculoId(Long veiculoId) {
		this.veiculoId = veiculoId;
	}



	public Double getLatitude() {
		return latitude;
	}



	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}



	public Double getLongitude() {
		return longitude;
	}



	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}



	
	
	
	
	
	
	
}
