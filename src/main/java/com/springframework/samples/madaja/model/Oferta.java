package com.springframework.samples.madaja.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.sun.istack.NotNull;

@Entity
@Table(name = "oferta")
public class Oferta extends NamedEntity {
	
	@Column(name = "descuento")
	@NotNull
	private Double descuento;
	
	@Column(name = "fecha_limite")   
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate fechaLimite;

	@Column(name = "hora_limite")   
	@DateTimeFormat(iso=ISO.TIME)
	private LocalTime horaLimite;
	
	@OneToMany(mappedBy = "oferta", cascade = CascadeType.ALL)
	private Set<Vehiculos> vehiculos;

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

	
	public Set<Vehiculos> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Set<Vehiculos> vehiculos) {
		this.vehiculos = vehiculos;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("descuento", descuento);
		builder.append("fechaLimite", fechaLimite);
		builder.append("horaLimite", horaLimite);
		builder.append("vehiculos", vehiculos);
		return builder.toString();
	}

	
	
	

}
