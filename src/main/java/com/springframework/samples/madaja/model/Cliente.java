package com.springframework.samples.madaja.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona{

	
	@Column(name = "esConflictivo") 
	protected String esConflictivo;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	private Set<ContratoVenta> contratoVentas;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	private Set<ContratoAlquiler> contratoAlquiler;
	
	//falta reserva

	public String getEsConflictivo() {
		return esConflictivo;
	}

	public void setEsConflictivo(String esConflictivo) {
		this.esConflictivo = esConflictivo;
	}
}
