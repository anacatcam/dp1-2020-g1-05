package com.springframework.samples.madaja.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "envio")
public class Envio extends Localizacion {
	
	@Column(name = "fecha")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate fecha;
	
	@Column(name = "hora")
//	@NotEmpty
	@DateTimeFormat(iso=ISO.TIME)
	private LocalTime hora;
	
	@OneToOne(mappedBy = "envio", optional = true)
	@JsonIgnore
	private Alquiler alquiler;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Mecanico mecanico;
	
	@OneToOne(mappedBy = "envio", optional = true)
	@JsonIgnore
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name = "estado_id")
	@JsonIgnore
	private EstadoEnvio estadoEnvio;

	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

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

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public EstadoEnvio getEstadoEnvio() {
		return estadoEnvio;
	}

	public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
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
