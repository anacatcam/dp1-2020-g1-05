package com.springframework.samples.madaja.model;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

import net.bytebuddy.asm.Advice.Local;

@Entity
@Table(name = "envio")
public class Envio extends Localizacion {
	
	@Column(name = "hora")
	@NotEmpty
	private LocalTime hora;
	@OneToOne(mappedBy = "envio", optional = true)
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
		builder.append("id", id);
		builder.append("getHora()", getHora());
		builder.append("getAlquiler()", getAlquiler());
		builder.append("getMecanico()", getMecanico());
		builder.append("getProvincia()", getProvincia());
		builder.append("getLocalidad()", getLocalidad());
		builder.append("getDireccion()", getDireccion());
		builder.append("getCodigoPostal()", getCodigoPostal());
		builder.append("getPais()", getPais());
		builder.append("getId()", getId());
		builder.append("isNew()", isNew());
		return builder.toString();
	}

}
