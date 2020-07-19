package com.aiko.apiolhovivo.util;

import com.aiko.apiolhovivo.entities.Parada;

public class ParadaDistance {
	
	private Parada parada;
	private Double distancia;
	
	public ParadaDistance(Parada parada, Double distancia) {
		super();
		this.parada = parada;
		this.distancia = distancia;
	}
	public Parada getParada() {
		return parada;
	}
	public void setParada(Parada parada) {
		this.parada = parada;
	}
	public Double getDistancia() {
		return distancia;
	}
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	
}
