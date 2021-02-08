package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@MappedSuperclass
public class Trabajador extends Person {

	@Column(name = "sueldo")
	@Positive
	@Min(value = 1)
	protected Double sueldo;

	public Double getSueldo() {
		return sueldo;
	}

	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}

}
