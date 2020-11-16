package com.springframework.samples.madaja.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;


@Entity
@Table(name = "gestor")
public class Gestor extends Trabajador{
	
	@ManyToMany(mappedBy = "gestores")
	private Set<Concesionario> concesionarios;

	public Set<Concesionario> getConcesionarios() {
		return concesionarios;
	}

	public void setConcesionarios(Set<Concesionario> concesionarios) {
		this.concesionarios = concesionarios;
	}

	public Gestor(Set<Concesionario> concesionarios) {
		super();
		this.concesionarios = concesionarios;
	}

	
}
