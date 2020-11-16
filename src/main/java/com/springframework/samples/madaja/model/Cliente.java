package com.springframework.samples.madaja.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona{

	
	@Column(name = "esConflictivo") 
	private String esConflictivo;
	
	@OneToOne
	@JoinColumn(name = "venta_id", nullable = false)
	private Venta venta;

	public String getEsConflictivo() {
		return esConflictivo;
	}

	public void setEsConflictivo(String esConflictivo) {
		this.esConflictivo = esConflictivo;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("esConflictivo", esConflictivo);
		builder.append("venta", venta);
		return builder.toString();
	}
	
	
		
}
