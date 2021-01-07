package com.springframework.samples.madaja.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "seguro_cliente")
public class SeguroCliente extends Seguro {
	
	@Column(name = "cobertura")
	@NotEmpty
	private String cobertura;
	
	@Column(name = "fecha_inicio")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate fechaInicio;
	
	@Column(name = "fecha_fin")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate fechaFin;

	
	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	private Vehiculos vehiculos;
	
	public String getCobertura() {
		return cobertura;
	}

	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public Vehiculos getVehiculos() {
		return vehiculos;
	}
	
	public void setVehiculos(Vehiculos vehiculos) {
		this.vehiculos = vehiculos;		
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("precio", precio);
		builder.append("cobertura", cobertura);
		builder.append("id", id);
		builder.append("getPrecio()", getPrecio());
		builder.append("getCobertura()", getCobertura());
		builder.append("getFranquicia()", getFranquicia());
		builder.append("getVehiculos()", getVehiculos());
		builder.append("getId()", getId());
		builder.append("isNew()", isNew());
		return builder.toString();
	}

	
}