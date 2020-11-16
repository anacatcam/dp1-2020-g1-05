package com.springframework.samples.madaja.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.websocket.OnError;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "alquiler")
public class Alquiler extends BaseEntity{ 
		
	@OneToOne
	@JoinColumn(name = "id_seguro_liente", nullable = false)
	private SeguroCliente seguro_cliente;
	
	@OneToOne
	@JoinColumn(name = "id_reserva", nullable = false)
	private Reserva reserva;
	
	@OneToOne
	@JoinColumn(name = "id_envio", nullable = true)
	private Envio envio;
	
	@OneToOne
	@JoinColumn(name = "id_recogida", nullable = true)
	private Recogida recogida;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Vehiculos vehiculo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente cliente;

	public SeguroCliente getSeguro_cliente() {
		return seguro_cliente;
	}

	public void setSeguro_cliente(SeguroCliente seguro_cliente) {
		this.seguro_cliente = seguro_cliente;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
	}

	public Recogida getRecogida() {
		return recogida;
	}

	public void setRecogida(Recogida recogida) {
		this.recogida = recogida;
	}

	public Vehiculos getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculos vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("seguro_cliente", seguro_cliente);
		builder.append("reserva", reserva);
		builder.append("envio", envio);
		builder.append("recogida", recogida);
		builder.append("vehiculo", vehiculo);
		builder.append("cliente", cliente);
		return builder.toString();
	}
	
	
}