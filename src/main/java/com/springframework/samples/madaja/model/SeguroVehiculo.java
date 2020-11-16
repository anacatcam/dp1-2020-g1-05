package com.springframework.samples.madaja.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SeguroVehiculo")
public class SeguroVehiculo extends Seguro { 
	
	@OneToOne(mappedBy = "seguroVehiculo", cascade = CascadeType.ALL)
	private Vehiculos vehiculo;

	public Vehiculos getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculos vehiculo) {
		this.vehiculo = vehiculo;
	}
	
}