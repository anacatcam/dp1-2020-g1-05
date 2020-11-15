package com.springframework.samples.madaja.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alquiler")
public class Alquiler extends BaseEntity{ //unir con reserva, seguro cliente, envio, recogida
	
	@OneToOne(mappedBy = "alquileres", cascade = CascadeType.ALL)
	private ContratoAlquiler contrato;
	
	@OneToOne(mappedBy = "alquileres", cascade = CascadeType.ALL)
	private SeguroCliente segCliente;
	
//	private Reserva reserva;
	
//	private Envio envio;
	
//	private Recogida recogida;
}
