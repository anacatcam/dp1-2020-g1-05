package com.springframework.samples.madaja.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table
@Entity(name = "venta")
public class Venta extends BaseEntity { //AÑADIR RESERVA

	@OneToOne
	@JoinColumn(name = "contrato_venta_id", unique = true)
	private ContratoVenta contratoVenta;

	public ContratoVenta getContratoVenta() {
		return contratoVenta;
	}

	public void setContratoVenta(ContratoVenta contratoVenta) {
		this.contratoVenta = contratoVenta;
	}	

}
