package com.springframework.samples.madaja.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "contrato_venta")
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
	
	@OneToOne
	@JoinColumn(name = "vehiculos_id", nullable = false)
	private Vehiculos vehiculos;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "contratoVenta")
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

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

	public Vehiculos getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Vehiculos vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	
	


}
