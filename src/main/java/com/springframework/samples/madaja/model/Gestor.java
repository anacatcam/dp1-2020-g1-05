package com.springframework.samples.madaja.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

@Entity
@Table(name = "gestor")
public class Gestor extends Trabajador {

	@ManyToMany(mappedBy = "gestores")
	private Set<Concesionario> concesionarios;

	protected Set<Concesionario> getConcesionariosInternal() {
		if (this.concesionarios == null) {
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

}
