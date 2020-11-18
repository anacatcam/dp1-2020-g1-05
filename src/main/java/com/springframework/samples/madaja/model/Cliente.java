package com.springframework.samples.madaja.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "cliente")
public class Cliente extends Persona{

	
	@Column(name = "esConflictivo") 
	private String esConflictivo;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Set<Venta> ventas;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Set<Alquiler> alquileres;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Set<Reserva> reservas;

	public String getEsConflictivo() {
		return esConflictivo;
	}

	public void setEsConflictivo(String esConflictivo) {
		this.esConflictivo = esConflictivo;
	}

	protected Set<Venta> getVentasInternal() {
		if (this.ventas == null) {
			this.ventas = new HashSet<>();
		}
		return this.ventas;
	}

	protected void setVentasInternal(Set<Venta> ventas) {
		this.ventas = ventas;
	}

	public List<Venta> getVentas() {
		List<Venta> sortedVentas = new ArrayList<>(getVentasInternal());
		PropertyComparator.sort(sortedVentas, new MutableSortDefinition("id", true, true));
		return Collections.unmodifiableList(sortedVentas);
	}

	public void addVentas(Venta venta) {
		getVentasInternal().add(venta);
		venta.setCliente(this);
	}
	
	public boolean removeVenta(Venta venta) {
		return getVentasInternal().remove(venta);
	}
	
	protected Set<Alquiler> getAlquileresInternal() {
		if (this.alquileres == null) {
			this.alquileres = new HashSet<>();
		}
		return this.alquileres;
	}

	protected void setAlquileresInternal(Set<Alquiler> alquiler) {
		this.alquileres = alquiler;
	}

	public List<Alquiler> getAlquileres() {
		List<Alquiler> sortedAlquileres = new ArrayList<>(getAlquileresInternal());
		PropertyComparator.sort(sortedAlquileres, new MutableSortDefinition("id", true, true));
		return Collections.unmodifiableList(sortedAlquileres);
	}

	public void addAlquiler(Alquiler alquiler) {
		getAlquileresInternal().add(alquiler);
		alquiler.setCliente(this);
	}
	
	public boolean removeAlquiler(Alquiler alquiler) {
		return getAlquileresInternal().remove(alquiler);
	}
	
	protected Set<Reserva> getReservasInternal() {
		if (this.reservas == null) {
			this.reservas = new HashSet<>();
		}
		return this.reservas;
	}

	protected void setReservasInternal(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Reserva> getReservas() {
		List<Reserva> sortedReservas = new ArrayList<>(getReservasInternal());
		PropertyComparator.sort(sortedReservas, new MutableSortDefinition("id", true, true));
		return Collections.unmodifiableList(sortedReservas);
	}

	public void addReserva(Reserva reserva) {
		getReservasInternal().add(reserva);
		reserva.setCliente(this);
	}
	
	public boolean removeReserva(Reserva reserva) {
		return getReservasInternal().remove(reserva);
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("esConflictivo", esConflictivo);
		builder.append("alquileres", alquileres);
		builder.append("reservas", reservas);
		builder.append("dni", dni);
		builder.append("nombre", nombre);
		builder.append("apellidos", apellidos);
		builder.append("telefono", telefono);
		builder.append("email", email);
		builder.append("getEsConflictivo()", getEsConflictivo());
		builder.append("getDni()", getDni());
		builder.append("getNombre()", getNombre());
		builder.append("getApellidos()", getApellidos());
		builder.append("getTelefono()", getTelefono());
		builder.append("getEmail()", getEmail());
		return builder.toString();
	}
	
}
