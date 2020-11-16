package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Compania")
public class Compania extends BaseEntity{
	
	@Column(name = "nombre")
	@NotEmpty
	private String nombre;
	
	@Column(name = "telefono")
	@NotEmpty
	private String telefono;
	
	@Column(name = "email")
	@Email
	private String email;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Compania(@NotEmpty String nombre, @NotEmpty String telefono, @Email String email) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
	}
	
	
}
