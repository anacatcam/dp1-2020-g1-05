package com.springframework.samples.madaja.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "estados_envio")
public class EstadoEnvio extends NamedEntity{

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoEnvio")
	private Set<Envio> envios;
	
	protected Set<Envio> getEnvioInternal(){
		if(this.envios == null) {
			this.envios = new HashSet<>();
		}
		return this.envios;
	}
	
	protected void setEnvioInternal(Set<Envio> envios) {
		this.envios = envios;
	}
}
