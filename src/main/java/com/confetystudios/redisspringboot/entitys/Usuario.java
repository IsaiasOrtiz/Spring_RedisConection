package com.confetystudios.redisspringboot.entitys;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Usuario")
public class Usuario {

	@Id
	private String id;
	private String name;
	private String correo;
	
//	 public enum Genero { 
//	        MASCULINO,FEMENINO
//	 }
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Usuario(String id, String name, String correo) {
		super();
		this.id = id;
		this.name = name;
		this.correo = correo;
	}
	
}
