package com.springframework.samples.madaja.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SeguroCliente")
public class SeguroCliente extends Seguro { //ASOCIADO 1 A 1 A CLIENTE
	
	@OneToOne
	@JoinColumn(name = "alquiler_id")
	private Alquiler alquileres;

	public Alquiler getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(Alquiler alquileres) {
		this.alquileres = alquileres;
	}
	
	
}