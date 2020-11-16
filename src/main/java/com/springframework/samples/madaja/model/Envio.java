package com.springframework.samples.madaja.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "envio")

public class Envio extends Localizacion {
	
	@Id
	@Column(name = "id")
	@NotEmpty
	private Integer id;
	
	@Column(name = "hora")
	@NotEmpty
	private LocalTime hora;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

}
