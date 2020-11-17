package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
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
	
	@Column(name = "cobertura")
	@NotEmpty
	protected String cobertura;
	
	@Column(name = "franquicia")
	@NotEmpty
	private String franquicia;

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

	public String getCobertura() {
		return cobertura;
	}

	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}

	public String getFranquicia() {
		return franquicia;
	}

	public void setFranquicia(String franquicia) {
		this.franquicia = franquicia;
	}


	
	
	
}