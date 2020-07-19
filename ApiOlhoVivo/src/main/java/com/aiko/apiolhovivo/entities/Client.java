package com.aiko.apiolhovivo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String usuario;
    private String senha;
    private String token;
    
    

    public Client() {
		super();
	}



	public Client(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}
	
	



	public Client(Long id, String usuario, String senha) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
	}



	public Client(Long id, String usuario, String senha, String token) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.token = token;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}

}
