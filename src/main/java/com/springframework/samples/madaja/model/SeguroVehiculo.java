package com.springframework.samples.madaja.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "seguro_vehiculo")
public class SeguroVehiculo extends Seguro {
	
	@Column(name = "numeroPoliza")
	@NotEmpty
	protected String numeroPoliza;
	
	@Column(name = "cobertura")
	@NotEmpty
	private String cobertura;
	
	@Column(name = "fecha_inicio")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotEmpty
	private LocalDate fechaInicio;
	
	@Column(name = "fecha_fin")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotEmpty
	private LocalDate fechaFin;
	
	@OneToOne(mappedBy = "seguroVehiculo", cascade = CascadeType.ALL, optional = true)
	private Vehiculos vehiculo;
	
	@ManyToOne
	private Compania compania;
	
	public String getNumeroPoliza() {
		return numeroPoliza;
	}

	public void setNumeroPoliza(String numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}
	
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

	public Vehiculos getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculos vehiculo) {
		this.vehiculo = vehiculo;
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
		builder.append("numeroPoliza", numeroPoliza);
		builder.append("vehiculo", vehiculo);
		builder.append("compania", compania);
		builder.append("numeroPoliza", numeroPoliza);
		builder.append("precio", precio);
		builder.append("franquicia", franquicia);
		builder.append("cobertura", cobertura);
		builder.append("fechaInicio", fechaInicio);
		builder.append("fechaFin", fechaFin);
		builder.append("id", id);
		builder.append("getNumeroPoliza()", getNumeroPoliza());
		builder.append("getVehiculo()", getVehiculo());
		builder.append("getCompania()", getCompania());
		builder.append("getNumeroPoliza()", getNumeroPoliza());
		builder.append("getPrecio()", getPrecio());
		builder.append("getCobertura()", getCobertura());
		builder.append("getFechaInicio()", getFechaInicio());
		builder.append("getFechaFin()", getFechaFin());
		builder.append("getFranquicia()", getFranquicia());
		builder.append("getId()", getId());
		builder.append("isNew()", isNew());
		return builder.toString();
	}
	
}