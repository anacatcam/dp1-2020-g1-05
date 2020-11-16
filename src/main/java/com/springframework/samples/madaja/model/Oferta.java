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

@Entity
@Table(name = "oferta")
public class Oferta extends NamedEntity {
	
	@Column(name = "descuento")
	private Double descuento;
	
	@Column(name = "fecha_limite")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaLimite;

	@Column(name = "hora_limite")        
	@DateTimeFormat(pattern = "hh:mm:ss")
	private LocalTime horaLimite;
	
	@OneToOne(mappedBy = "oferta", cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Vehiculos vehiculo;

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public LocalDate getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public LocalTime getHoraLimite() {
		return horaLimite;
	}

	public void setHoraLimite(LocalTime horaLimite) {
		this.horaLimite = horaLimite;
	}

	public Vehiculos getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculos vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("descuento", descuento);
		builder.append("fechaLimite", fechaLimite);
		builder.append("horaLimite", horaLimite);
		builder.append("vehiculo", vehiculo);
		return builder.toString();
	}
	
	

}
