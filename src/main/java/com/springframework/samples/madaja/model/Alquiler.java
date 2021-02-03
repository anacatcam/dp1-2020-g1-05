package com.springframework.samples.madaja.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "alquiler")
public class Alquiler extends BaseEntity {

	@Column(name = "fecha_inicio")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate fechaInicio;
	
	@Column(name = "fecha_fin")
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate fechaFin;

	@Column(name = "limite_KM")
	@Positive
	@Min(value = 1)
	private Integer limiteKM;

	@Column(name = "dep_lleno")
	@NotNull
	private Boolean depLleno;
	
	@Column(name = "devuelto")
	@NotNull
	private Boolean devuelto;

	@OneToOne
	@JoinColumn(name = "reserva_id", nullable = true)
	@JsonIgnore
	private Reserva reserva;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "envio_id", nullable = true)
	@JsonIgnore
	private Envio envio;

	@OneToOne
	@JoinColumn(name = "recogida_id", nullable = true)
	@JsonIgnore
	private Recogida recogida;

	@ManyToOne(cascade = CascadeType.ALL)
	private Vehiculos vehiculo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Cliente cliente;

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getLimiteKM() {
		return limiteKM;
	}

	public void setLimiteKM(Integer limiteKM) {
		this.limiteKM = limiteKM;
	}

	public Boolean getDepLleno() {
		return depLleno;
	}

	public void setDepLleno(Boolean depLleno) {
		this.depLleno = depLleno;
	}
	
	public Boolean getDevuelto() {
		return devuelto;
	}

	public void setDevuelto(Boolean devuelto) {
		this.devuelto = devuelto;
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
		builder.append("reserva", reserva);
		builder.append("envio", envio);
		builder.append("recogida", recogida);
		builder.append("vehiculo", vehiculo);
		builder.append("cliente", cliente);
		return builder.toString();
	}

}