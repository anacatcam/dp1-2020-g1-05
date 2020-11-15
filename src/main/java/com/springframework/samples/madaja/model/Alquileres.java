package com.springframework.samples.madaja.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alquileres")
public class Alquileres extends BaseEntity{ //unir con reserva, seguro cliente, envio, recogida
	
	@OneToOne(mappedBy = "alquiler", cascade = CascadeType.ALL)
	private ContratosAlquiler contrato;
	
	@OneToOne(mappedBy = "alquiler", cascade = CascadeType.ALL)
	private SeguroCliente segCliente;
	
//	private Reserva reserva;
	
//	private Envio envio;
	
//	private Recogida recogida;
}
