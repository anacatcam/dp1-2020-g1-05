package com.springframework.samples.madaja.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name = "contratos alquiler")
public class ContratosAlquiler extends BaseEntity { // cliente y vehiculo y generar tostring

	
	@Column(name = "fechaInicio")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotEmpty
	private LocalDate fechaIn;
	
	@Column(name = "fechaFin")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotEmpty
	private LocalDate fechaFin;
	
	@Column(name = "depositoLleno")
	private Boolean depLleno;
	
	@Column(name = "limiteKM")
	private Integer limiteKm;
	
	@OneToOne
	@JoinColumn(name = "alquiler_id", unique = true)
	private Alquiler alquiler;
	
	@ManyToOne
	@JoinColumn(name = "vehiculo_id", unique = true)
	private Vehiculos vehiculos;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", unique = true)
	private Cliente cliente;

	public LocalDate getFechaIn() {
		return fechaIn;
	}

	public void setFechaIn(LocalDate fechaIn) {
		this.fechaIn = fechaIn;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Boolean getDepLleno() {
		return depLleno;
	}

	public void setDepLleno(Boolean depLleno) {
		this.depLleno = depLleno;
	}

	public Integer getLimiteKm() {
		return limiteKm;
	}

	public void setLimiteKm(Integer limiteKm) {
		this.limiteKm = limiteKm;
	}

	public Alquiler getAlquiler() {
		return alquiler;
	}

	public void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}

	public Vehiculos getVehiculo() {
		return vehiculos;
	}

	public void setVehiculo(Vehiculos vehiculo) {
		this.vehiculos = vehiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	


}
