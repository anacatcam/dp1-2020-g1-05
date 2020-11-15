package com.springframework.samples.madaja.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(mappedBy = "incidencias")
	private Set<Mecanico> mecanicos;

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
	
	
	public void setMecanicos(Set<Mecanico> mecanicos) {
		this.mecanicos = mecanicos;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("descripcion", descripcion);
		builder.append("solucionada", solucionada);
		builder.append("vehiculos", vehiculos);
		builder.append("mecanicos", mecanicos);
		return builder.toString();
	}

	
	
}
