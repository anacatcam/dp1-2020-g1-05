package com.springframework.samples.madaja.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	public String toString() {
		return new ToStringCreator(this)
				
				.append("id", this.getId()).append("new", this.isNew()).append("name", this.getName())
				.append(descuento.toString(), this.descuento).append(fechaLimite.toString(), this.fechaLimite)
				.append(horaLimite.toString(), this.horaLimite)
				.toString();
	}

}
