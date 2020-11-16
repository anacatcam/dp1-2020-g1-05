package com.springframework.samples.madaja.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;


@Entity
@Table(name = "concesionario")
public class Concesionario extends Localizacion {
	
	@Column(name = "email")
	@NotEmpty
	private String email;
	
	@Column(name = "telefono")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telefono;
	
	@JoinTable(name = "concesionarios_gestores", joinColumns = @JoinColumn(name = "concesionario_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "gestor_id", nullable = false))
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Gestor> gestores;

	protected Set<Gestor> getGestoresInternal(){
		if(this.gestores == null) {
			this.gestores = new HashSet<Gestor>();
		}
		return this.gestores;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Set<Gestor> getGestores() {
		return gestores;
	}

	public void setGestores(Set<Gestor> gestores) {
		this.gestores = gestores;
	}

	public Concesionario(@NotEmpty String email, @NotEmpty @Digits(fraction = 0, integer = 10) String telefono,
			Set<Gestor> gestores) {
		super();
		this.email = email;
		this.telefono = telefono;
		this.gestores = gestores;
	}
	
	

}
