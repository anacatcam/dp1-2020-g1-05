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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Indexed;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cliente")
@Indexed
public class Cliente extends Person {

	@Column(name = "esConflictivo")
	@NotNull
	private String esConflictivo;

	@Column(name = "dias_retraso")
	@NotNull
	@Min(0)
	private Integer diasRetraso;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Venta> ventas;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Alquiler> alquileres;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Reserva> reservas;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private User user;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Incidencia> numIncidencias;

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

	protected Set<Incidencia> getIncidenciasInternal() {
		if (this.numIncidencias == null) {
			this.numIncidencias = new HashSet<>();
		}
		return this.numIncidencias;
	}

	protected void setIncidenciasInternal(Set<Incidencia> incidencias) {
		this.numIncidencias = incidencias;
	}

	public List<Incidencia> getIncidencias() {
		List<Incidencia> sortedIncidencias = new ArrayList<>(getIncidenciasInternal());
		PropertyComparator.sort(sortedIncidencias, new MutableSortDefinition("id", true, true));
		return Collections.unmodifiableList(sortedIncidencias);
	}

	public void addIncidencia(Incidencia incidencia) {
		getIncidenciasInternal().add(incidencia);
		incidencia.setCliente(this);
	}

	public boolean removeIncidencia(Incidencia incidencia) {
		return getIncidenciasInternal().remove(incidencia);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getDiasRetraso() {
		return this.diasRetraso;
	}

	public void setDiasRetraso(Integer dias) {
		this.diasRetraso = dias;
	}

}
