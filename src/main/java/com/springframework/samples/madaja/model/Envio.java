package com.springframework.samples.madaja.model;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "envio")

public class Envio extends Localizacion {
	
	@Column(name = "hora")
	@NotEmpty
	private LocalTime hora;
	
	@OneToOne(mappedBy = "envio", cascade = CascadeType.ALL)
	private Alquiler alquiler;

	@ManyToOne(cascade = CascadeType.ALL)
	private Mecanico mecanico;

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Alquiler getAlquiler() {
		return alquiler;
	}

	public void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	public void setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public Envio(@NotEmpty LocalTime hora, Alquiler alquiler, Mecanico mecanico) {
		super();
		this.hora = hora;
		this.alquiler = alquiler;
		this.mecanico = mecanico;
	}
	
	

}
