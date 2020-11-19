package com.springframework.samples.madaja.model;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "recogida")
public class Recogida extends Localizacion {
	
	@Column(name = "hora")
	@NotEmpty
	private LocalTime hora;

	@OneToOne(mappedBy = "recogida", optional = false)
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

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("hora", hora);
		builder.append("alquiler", alquiler);
		builder.append("mecanico", mecanico);
		return builder.toString();
	}

	
}
