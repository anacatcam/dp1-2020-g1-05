package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@MappedSuperclass
public class Seguro extends BaseEntity{ 

	@Column(name = "numeroPoliza")
	@NotEmpty
	protected String numeroPoliza;
	
	@Column(name = "precio")
	@NotEmpty
	@Positive
	protected Double precio;
	
	@Column(name = "franquicia")
	@NotNull
	protected Integer franquicia;

	public String getNumeroPoliza() {
		return numeroPoliza;
	}

	public void setNumeroPoliza(String numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getFranquicia() {
		return franquicia;
	}

	public void setFranquicia(Integer franquicia) {
		this.franquicia = franquicia;
	}



}