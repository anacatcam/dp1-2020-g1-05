package com.springframework.samples.madaja.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;


@Entity
@Table(name = "gestor")
public class Gestor extends Trabajador{
	
	@ManyToMany(mappedBy = "gestores")
	private Set<Concesionario> concesionarios;

	protected Set<Concesionario> getConcesionariosInternal(){
		if(this.concesionarios == null) {
			this.concesionarios = new HashSet<>();
		}
		return this.concesionarios;
	}
	
	protected void setConcesionariosInternal(Set<Concesionario> concesionarios) {
		this.concesionarios = concesionarios;
	}

	public List<Concesionario> getConcesionarios() {
		List<Concesionario> sortedConcesionarios = new ArrayList<>(getConcesionariosInternal());
		PropertyComparator.sort(sortedConcesionarios, new MutableSortDefinition("id", true, true));
		return Collections.unmodifiableList(sortedConcesionarios);
	}

	public void addConcesionario(Concesionario concesionario) {
		getConcesionariosInternal().add(concesionario);
	}
	
	public boolean removeConcesionario(Concesionario concesionario) {
		return getConcesionariosInternal().remove(concesionario);
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("concesionarios", concesionarios);
		builder.append("sueldo", sueldo);
		builder.append("dni", dni);
		builder.append("nombre", nombre);
		builder.append("apellidos", apellidos);
		builder.append("telefono", telefono);
		builder.append("email", email);
		builder.append("getSueldo()", getSueldo());
		builder.append("getDni()", getDni());
		builder.append("getNombre()", getNombre());
		builder.append("getApellidos()", getApellidos());
		builder.append("getTelefono()", getTelefono());
		builder.append("getEmail()", getEmail());
		return builder.toString();
	}
	
}
