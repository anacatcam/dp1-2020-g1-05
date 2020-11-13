package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "incidencia")
public class Incidencia extends BaseEntity {
	
	@Column(name = "descripcion")
	@NotEmpty
	private String descripcion;
	
	@Column(name = "solucionada")
	@NotEmpty
	private Boolean solucionada;
	
	@ManyToOne
	@JoinColumn(name = "vehiculos_id")
	private Vehiculos vehiculos;
	
	//FALTA POR AÑADIR GESTOR

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getSolucionada() {
		return solucionada;
	}

	public void setSolucionada(Boolean solucionada) {
		this.solucionada = solucionada;
	}

	public Vehiculos getVehiculo() {
		return vehiculos;
	}

	public void setVehiculo(Vehiculos vehiculo) {
		this.vehiculos = vehiculo;
	}
	
	
	public String toString() {
		return new ToStringCreator(this)
				
				.append("id", this.id).append("new", this.isNew()).append(descripcion, this.descripcion)
				.append(solucionada.toString(), this.solucionada).append(vehiculos.toString(), this.vehiculos).toString();
	}
	
}
