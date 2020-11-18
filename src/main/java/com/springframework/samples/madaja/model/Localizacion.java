package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

@MappedSuperclass
public class Localizacion extends BaseEntity{
	
	@Column(name = "provincia")
	@NotEmpty
	private String provincia;
	
	@Column(name = "localidad")
	@NotEmpty
	private String localidad;
	
	@Column(name = "direccion")
	@NotEmpty
	private String direccion;
	
	@Column(name = "codigoPostal")
	@NotEmpty
	private String codigoPostal;
	
	@Column(name = "pais")
	@NotEmpty
	private String pais;

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}




}
