package com.springframework.samples.madaja.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "SeguroVehiculo")
public class SeguroVehiculo extends Seguro { 
	
	@OneToOne(mappedBy = "seguroVehiculo", cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Vehiculos vehiculo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Compania compania;

	public Vehiculos getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculos vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Compania getCompania() {
		return compania;
	}

	public void setCompania(Compania compania) {
		this.compania = compania;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("vehiculo", vehiculo);
		builder.append("compania", compania);
		return builder.toString();
	}
	
	
	
}