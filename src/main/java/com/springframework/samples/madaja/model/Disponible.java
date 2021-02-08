package com.springframework.samples.madaja.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "disponible")
public class Disponible extends NamedEntity {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "disponible")
	@JsonIgnore
	private Set<Vehiculos> vehiculos;

	protected Set<Vehiculos> getVehiculosInternal() {
		if (this.vehiculos == null) {
			this.vehiculos = new HashSet<>();
		}
		return this.vehiculos;
	}

	protected void setVehiculosInternal(Set<Vehiculos> vehiculos) {
		this.vehiculos = vehiculos;
	}

}