package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@MappedSuperclass
public class Seguro extends BaseEntity{ 
	
	@Column(name = "precio")
	@PositiveOrZero
	@NotNull
	protected Double precio;
	
	@Column(name = "franquicia")
	@NotNull
	@PositiveOrZero
	protected Integer franquicia;

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