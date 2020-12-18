package com.springframework.samples.madaja.model;

import java.time.LocalDate;

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
@Table(name = "seguro_cliente")
public class SeguroCliente extends Seguro {
	
	@Column(name = "cobertura")
	@NotEmpty
	private String cobertura;
	
	@Column(name = "fecha_inicio")
	@NotEmpty
	private LocalDate fechaInicio;
	
	@Column(name = "fecha_fin")
	@NotEmpty
	private LocalDate fechaFin;
	
	@OneToOne
	@JoinColumn(name = "alquiler_id", nullable = false)
	private Alquiler alquiler;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Compania compania;
	
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

	public Alquiler getAlquiler() {
		return alquiler;
	}

	public void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}

	public Compania getCompania() {
		return compania;
	}

	public void setCompania(Compania compania) {
		this.compania = compania;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("alquiler", alquiler);
		builder.append("compania", compania);
		builder.append("numeroPoliza", numeroPoliza);
		builder.append("precio", precio);
		builder.append("cobertura", cobertura);
		builder.append("id", id);
		builder.append("getAlquiler()", getAlquiler());
		builder.append("getCompania()", getCompania());
		builder.append("getNumeroPoliza()", getNumeroPoliza());
		builder.append("getPrecio()", getPrecio());
		builder.append("getCobertura()", getCobertura());
		builder.append("getFranquicia()", getFranquicia());
		builder.append("getId()", getId());
		builder.append("isNew()", isNew());
		return builder.toString();
	}
	
}