package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.springframework.core.style.ToStringCreator;


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
	@NotEmpty
	protected String franquicia;

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

	public String getFranquicia() {
		return franquicia;
	}

	public void setFranquicia(String franquicia) {
		this.franquicia = franquicia;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("numeroPoliza", numeroPoliza);
		builder.append("precio", precio);
		builder.append("franquicia", franquicia);
		builder.append("id", id);
		builder.append("getNumeroPoliza()", getNumeroPoliza());
		builder.append("getPrecio()", getPrecio());
		builder.append("getFranquicia()", getFranquicia());
		builder.append("getId()", getId());
		builder.append("isNew()", isNew());
		return builder.toString();
	}
	
}