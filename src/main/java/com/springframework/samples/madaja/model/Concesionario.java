package com.springframework.samples.madaja.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "concesionario")
public class Concesionario extends BaseEntity/* esto se borra luego y se pone extends Localizacion*/ {
	
	@Column(name = "email")
	@NotEmpty
	private String email;
	
	@Column(name = "telefono")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telefono;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "concesionario")
	private Set<Vehiculos> vehiculos;
	
	//FALTA POR AÑADIR GESTOR
	
	protected Set<Vehiculos> getVehiculosInternal() {
		if (this.vehiculos == null) {
			this.vehiculos = new HashSet<>();
		}
		return this.vehiculos;
	}

	protected void setVehiculosInternal(Set<Vehiculos> vehiculos) {
		this.vehiculos = vehiculos;
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
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
				
				.append("id", this.getId()).append(email, this.email).append(telefono, this.telefono).toString();
	}
	
}
