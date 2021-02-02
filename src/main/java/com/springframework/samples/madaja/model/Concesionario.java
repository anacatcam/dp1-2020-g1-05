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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "concesionario")
public class Concesionario extends Localizacion {
	
	@Column(name = "nombre")
	@NotEmpty
	private String nombre;

	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;
	
	@Column(name = "telefono")
	@NotEmpty
	@Length(min = 9,max=9)
	@Digits(fraction = 0, integer = 10)
	private String telefono;
	
	@JoinTable(name = "concesionarios_gestores", 
			joinColumns = @JoinColumn(name = "concesionario_id", nullable = false), 
			inverseJoinColumns = @JoinColumn(name = "gestor_id", nullable = false))
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Gestor> gestores;
	
	@OneToMany(mappedBy = "concesionario", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Vehiculos> vehiculos;
	
	protected Set<Vehiculos> getVehiculosInternal(){
		if(this.vehiculos == null) {
			this.vehiculos = new HashSet<>();
		}
		return this.vehiculos;
	}
	
	protected void setVehiculosInternal(Set<Vehiculos> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<Vehiculos> getVehiculos() {
		List<Vehiculos> sortedVehiculos = new ArrayList<>(getVehiculosInternal());
		PropertyComparator.sort(sortedVehiculos, new MutableSortDefinition("id", true, true));
		return Collections.unmodifiableList(sortedVehiculos);
	}

	public void addVehiculo(Vehiculos vehiculos) {
		getVehiculosInternal().add(vehiculos);
		vehiculos.setConcesionario(this);
	}
	
	public boolean removeVehiculo(Vehiculos vehiculos) {
		return getVehiculosInternal().remove(vehiculos);
	}

	protected Set<Gestor> getGestoresInternal(){
		if(this.gestores == null) {
			this.gestores = new HashSet<>();
		}
		return this.gestores;
	}
	
	protected void setGestoresInternal(Set<Gestor> gestores) {
		this.gestores = gestores;
	}

	public List<Gestor> getGestores() {
		List<Gestor> sortedGestores = new ArrayList<>(getGestoresInternal());
		PropertyComparator.sort(sortedGestores, new MutableSortDefinition("nombre", true, true));
		return Collections.unmodifiableList(sortedGestores);
	}

	public void addGestor(Gestor gestor) {
		getGestoresInternal().add(gestor);
	}
	
	public boolean removeGestor(Gestor gestor) {
		return getGestoresInternal().remove(gestor);
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	@Transient
	public String getFullLugar() {
		return localidad + ", " + provincia;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("nombre", nombre);
		builder.append("email", email);
		builder.append("telefono", telefono);
		builder.append("gestores", gestores);
		builder.append("vehiculos", vehiculos);
		return builder.toString();
	}

//	@Override
//	public String toString() {
//		ToStringCreator builder = new ToStringCreator(this);
//		builder.append("email", email);
//		builder.append("telefono", telefono);
//		builder.append("vehiculos", vehiculos);
//		builder.append("id", id);
//		builder.append("getEmail()", getEmail());
//		builder.append("getTelefono()", getTelefono());
//		builder.append("getId()", getId());
//		builder.append("isNew()", isNew());
//		return builder.toString();
//	}
//	
	
	

}
