package com.springframework.samples.madaja.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reserva")
public class Reserva extends BaseEntity {

	@Column(name = "fianza")
	@Positive
	private Double fianza;

	@Column(name = "fechaGastos")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate fechaGastos;
	
	@OneToOne(mappedBy = "reserva", optional = true)
	@JsonIgnore
	private Alquiler alquiler;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Cliente cliente;

	@OneToOne(mappedBy = "reserva", optional = true)
	@JsonIgnore
	private Venta venta;

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

	public Alquiler getAlquiler() {
		return alquiler;
	}

	public void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("id", id);
		builder.append("fianza", fianza);
		builder.append("fechaGastos", fechaGastos);
		builder.append("alquiler", alquiler);
		builder.append("cliente", cliente);
		builder.append("venta", venta);
		return builder.toString();
	}

}
