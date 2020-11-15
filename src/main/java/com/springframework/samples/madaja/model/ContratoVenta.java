package com.springframework.samples.madaja.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

public class ContratoVenta extends BaseEntity {
		
	@Column(name="precio")
	@NotEmpty
	@PositiveOrZero
	private Double precio;
	
	@Column(name="fianza")
	@NotEmpty
	@PositiveOrZero
	private Double fianza;
	
	@Column(name="fecha")
	@NotEmpty
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@FutureOrPresent
	private LocalDate fecha;

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getFianza() {
		return fianza;
	}

	public void setFianza(Double fianza) {
		this.fianza = fianza;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	


}
