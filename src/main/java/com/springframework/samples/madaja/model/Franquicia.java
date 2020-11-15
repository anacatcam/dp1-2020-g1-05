package com.springframework.samples.madaja.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.springframework.core.style.ToStringCreator;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

@Entity
@Table(name = "franquicia")
public class Franquicia extends BaseEntity{ //ASOCIADO A SEGURO
	
	
	@Column(name="tarifa")
	@NotEmpty
	@Positive
	protected Integer tarifaFija;
	
	@Column(name="nombre")
	@NotEmpty
	protected String nombre;
	
	@Column(name="telefono")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	protected String telefono;
	
	@Column(name="email")
	@NotEmpty
	@Email
	protected String email;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "franquicia")
	private Set<Seguro> seguros;

	public Integer getTarifaFija() {
		return tarifaFija;
	}

	public void setTarifaFija(Integer tarifaFija) {
		this.tarifaFija = tarifaFija;
	}

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

	public Set<Seguro> getSeguros() {
		return seguros;
	}

	public void setSeguros(Set<Seguro> seguros) {
		this.seguros = seguros;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("tarifaFija", tarifaFija);
		builder.append("nombre", nombre);
		builder.append("telefono", telefono);
		builder.append("email", email);
		builder.append("seguros", seguros);
		return builder.toString();
	}
	
	
	
}