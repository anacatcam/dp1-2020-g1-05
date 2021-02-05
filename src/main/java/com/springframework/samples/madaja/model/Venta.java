package com.springframework.samples.madaja.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "venta")
public class Venta extends BaseEntity {

	@Column(name = "fecha")
	// @Temporal(TemporalType.DATE)
	private LocalDate fecha;

	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente cliente;

	@OneToOne(mappedBy = "venta", optional = true)
	private Reserva reserva;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "envio_id", nullable = true)
	private Envio envio;

	@ManyToOne(cascade = CascadeType.ALL)
	private Vehiculos vehiculo;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

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

	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		this.envio = envio;
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
		builder.append("id", id);
		builder.append("getCliente()", getCliente());
		builder.append("getReserva()", getReserva());
		builder.append("getVehiculo()", getVehiculo());
		builder.append("getId()", getId());
		builder.append("isNew()", isNew());
		return builder.toString();
	}

}
