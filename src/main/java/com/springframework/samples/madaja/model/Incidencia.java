package com.springframework.samples.madaja.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
import javax.validation.constraints.NotNull;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "incidencia")
public class Incidencia extends BaseEntity {
	
	@Column(name = "descripcion")
	@NotEmpty
	private String descripcion;
	
	@Column(name = "solucionada")
	@NotNull
	private Boolean solucionada;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	private Vehiculos vehiculos;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	private Cliente cliente;
	
	@JoinTable(name = "incidencias_mecanicos", 
			joinColumns = @JoinColumn(name = "incidencia_id", nullable = false), 
			inverseJoinColumns = @JoinColumn(name = "mecanico_id", nullable = false))
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Mecanico> mecanicos;

	protected Set<Mecanico> getMecanicosInternal(){
		if(this.mecanicos == null) {
			this.mecanicos = new HashSet<>();
		}
		return this.mecanicos;
	}
	
	protected void setMecanicosInternal(Set<Mecanico> mecanicos) {
		this.mecanicos = mecanicos;
	}

	public List<Mecanico> getMecanicos() {
		List<Mecanico> sortedMecanicos = new ArrayList<>(getMecanicosInternal());
		PropertyComparator.sort(sortedMecanicos, new MutableSortDefinition("id", true, true));
		return Collections.unmodifiableList(sortedMecanicos);
	}

	public void addMecanico(Mecanico mecanico) {
		getMecanicosInternal().add(mecanico);
	}
	
	public boolean removeMecanicos(Mecanico mecanico) {
		return getMecanicosInternal().remove(mecanico);
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
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("descripcion", descripcion);
		builder.append("solucionada", solucionada);
		builder.append("vehiculos", vehiculos);
		builder.append("id", id);
		builder.append("getDescripcion()", getDescripcion());
		builder.append("getSolucionada()", getSolucionada());
		builder.append("getVehiculos()", getVehiculos());
		builder.append("getId()", getId());
		builder.append("isNew()", isNew());
		return builder.toString();
	}
	
	
	
}
