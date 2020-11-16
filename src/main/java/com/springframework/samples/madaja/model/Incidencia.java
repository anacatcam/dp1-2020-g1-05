package com.springframework.samples.madaja.model;

import java.util.HashSet;
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Vehiculos vehiculos;
	
	@JoinTable(name = "incidencias_mecanicos", joinColumns = @JoinColumn(name = "incidencia_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "mecanico_id", nullable = false))
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Mecanico> mecanicos;

	protected Set<Mecanico> getMecanicosInternal(){
		if(this.mecanicos == null) {
			this.mecanicos = new HashSet<Mecanico>();
		}
		return this.mecanicos;
	}

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

	public Vehiculos getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Vehiculos vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Set<Mecanico> getMecanicos() {
		return mecanicos;
	}

	public void setMecanicos(Set<Mecanico> mecanicos) {
		this.mecanicos = mecanicos;
	}

	public Incidencia(@NotEmpty String descripcion, @NotEmpty Boolean solucionada, Vehiculos vehiculos,
			Set<Mecanico> mecanicos) {
		super();
		this.descripcion = descripcion;
		this.solucionada = solucionada;
		this.vehiculos = vehiculos;
		this.mecanicos = mecanicos;
	}

	
	
	
}
