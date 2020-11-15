package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona{

	
	@Column(name = "esConflictivo") 
	protected String esConflictivo; //PREGUNTAR SI LLEVARIA ALGUNA RESTRICCIÓN @

	public String getEsConflictivo() {
		return esConflictivo;
	}

	public void setEsConflictivo(String esConflictivo) {
		this.esConflictivo = esConflictivo;
	}
}
