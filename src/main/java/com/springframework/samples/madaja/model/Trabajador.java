package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

import com.sun.istack.NotNull;

@MappedSuperclass
public class Trabajador extends Persona{
	
	@Column(name = "sueldo")
	@NotNull
	protected Double sueldo;

	public Double getSueldo() {
		return sueldo;
	}

	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}


	
}
