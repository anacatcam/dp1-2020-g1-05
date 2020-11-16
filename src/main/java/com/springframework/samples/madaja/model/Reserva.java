package com.springframework.samples.madaja.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "reserva")
public class Reserva {
	
	@Id
	@Column(name = "id")
	@NotEmpty
	private Integer id;
	
	@Column(name = "fianza")
	@NotEmpty
	private Double fianza;
	
	@Column(name = "fechaGastos")
	@NotEmpty
	private LocalDate fechaGastos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getFianza() {
		return fianza;
	}

	public void setFianza(Double fianza) {
		this.fianza = fianza;
	}

	public LocalDate getFechaGastos() {
		return fechaGastos;
	}

	public void setFechaGastos(LocalDate fechaGastos) {
		this.fechaGastos = fechaGastos;
	}
	
}
