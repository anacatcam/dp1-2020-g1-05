package com.springframework.samples.madaja.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;




@Entity
@Table(name = "mecanicos")
public class Mecanico extends Trabajador{

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "mecanico_incidencia", joinColumns = @JoinColumn(name = "mecanico_id"),
			inverseJoinColumns = @JoinColumn(name = "incidencia_id"))
	private Set<Incidencia> incidencias;

	public Set<Incidencia> getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(Set<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("incidencias", incidencias);
		return builder.toString();
	}
	
	
}
