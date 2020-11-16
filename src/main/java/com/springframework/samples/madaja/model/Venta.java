package com.springframework.samples.madaja.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

@Table
@Entity(name = "venta")
public class Venta extends BaseEntity { 
	
	@OneToOne(mappedBy = "venta", cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Cliente cliente;
	
	@OneToOne(mappedBy = "venta", cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Reserva reserva;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Vehiculos vehiculo;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Vehiculos getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculos vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("cliente", cliente);
		builder.append("reserva", reserva);
		builder.append("vehiculo", vehiculo);
		return builder.toString();
	}
	
	

}
