package com.springframework.samples.madaja.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "compania")
public class Compania extends BaseEntity{
	
	@Column(name = "nombre")
	@NotEmpty
	private String nombre;
	
	@Column(name = "telefono")
	@NotEmpty
	private String telefono;
	
	@Column(name = "email")
	@Email
	private String email;
	
	@OneToMany(mappedBy = "compania", cascade = CascadeType.ALL)
	private Set<SeguroVehiculo> segurosVehiculo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	protected Set<SeguroVehiculo> getSegurosVehiculoInternal() {
		if (this.segurosVehiculo == null) {
			this.segurosVehiculo = new HashSet<>();
		}
		return this.segurosVehiculo;
	}

	protected void setSegurosVehiculoInternal(Set<SeguroVehiculo> segurosVehiculo) {
		this.segurosVehiculo = segurosVehiculo;
	}

	public List<SeguroVehiculo> getSegurosVehiculo() {
		List<SeguroVehiculo> sortedSegurosVehiculo = new ArrayList<>(getSegurosVehiculoInternal());
		PropertyComparator.sort(sortedSegurosVehiculo, new MutableSortDefinition("id", true, true));
		return Collections.unmodifiableList(sortedSegurosVehiculo);
	}

	public void addSeguroVehiculo(SeguroVehiculo segurosVehiculo) {
		getSegurosVehiculoInternal().add(segurosVehiculo);
		segurosVehiculo.setCompania(this);
	}
	
	public boolean removeSeguroVehiculo(SeguroVehiculo seguroVehiculo) {
		return getSegurosVehiculoInternal().remove(seguroVehiculo);
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("nombre", nombre);
		builder.append("telefono", telefono);
		builder.append("email", email);
		builder.append("id", id);
		builder.append("getNombre()", getNombre());
		builder.append("getTelefono()", getTelefono());
		builder.append("getEmail()", getEmail());
		builder.append("getId()", getId());
		builder.append("isNew()", isNew());
		return builder.toString();
	}
	
}
