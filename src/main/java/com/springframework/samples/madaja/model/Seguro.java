package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.springframework.core.style.ToStringCreator;

@MappedSuperclass
public class Seguro{ //ASOCIADO A FRANQUICIA

	@Id
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
	
	@ManyToOne
	@JoinColumn(name = "franquicia_id")
	private Franquicia franquicia;

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

	public Franquicia getFranquicia() {
		return franquicia;
	}

	public void setFranquicia(Franquicia franquicia) {
		this.franquicia = franquicia;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("numeroPoliza", numeroPoliza);
		builder.append("precio", precio);
		builder.append("cobertura", cobertura);
		builder.append("franquicia", franquicia);
		return builder.toString();
	}

	
	
	
	
}