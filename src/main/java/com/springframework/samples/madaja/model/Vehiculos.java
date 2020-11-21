 package com.springframework.samples.madaja.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "vehiculos")
public class Vehiculos extends BaseEntity{
	
	@Column(name = "matricula")
	@NotEmpty
	private String matricula;
	
	@Column(name = "precio_alquiler")
	private Integer precioAlquiler;
	
	@Column(name = "precio_venta")
	private Integer precioVenta;
	
	@Column(name = "marca")
	@NotEmpty
	private String marca;
	
	@Column(name = "modelo")
	@NotEmpty
	private String modelo;
	
	@Column(name = "puertas")
	private Integer puertas;
	
	@Column(name = "plazas")
	private Integer plazas;
	
	@ManyToOne
	@JoinColumn(name = "cambio_id")
	private Cambio cambio;
	
	@Column(name = "maletero")
	private Integer maletero;
	
	@Column(name = "km_actuales")
	private Integer kmActuales;
	
	@Column(name = "caracteristicas")
	@NotEmpty
	private String caracteristicas;
	
	@Column(name = "estado")
	@NotEmpty
	private String estado;
	
	@ManyToOne
	@JoinColumn(name = "disponible_id")
	private Disponible disponible;
	
	@ManyToOne
	@JoinColumn(name = "combustible_id")
	private Combustible combustible;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private Concesionario concesionario;	
	
	@OneToOne(mappedBy = "vehiculo", cascade = CascadeType.ALL)
	private Oferta oferta;
	
	@OneToOne
	@JoinColumn(name = "seguro_vehiculo_id", unique = true, nullable = true)
	private SeguroVehiculo seguro_vehiculo;
	
	@OneToMany(mappedBy = "vehiculos", cascade = CascadeType.ALL)
	private Set<Incidencia> incidencias;

	@OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
	private Set<Venta> ventas;
	
	@OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
	private Set<Alquiler> alquileres;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getPrecioAlquiler() {
		return precioAlquiler;
	}

	public void setPrecioAlquiler(Integer precioAlquiler) {
		this.precioAlquiler = precioAlquiler;
	}

	public Integer getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Integer precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getPuertas() {
		return puertas;
	}

	public void setPuertas(Integer puertas) {
		this.puertas = puertas;
	}
	
	public Integer getPlazas() {
		return plazas;
	}

	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}

	public Cambio getCambio() {
		return cambio;
	}

	public void setCambio(Cambio cambio) {
		this.cambio = cambio;
	}

	public Integer getMaletero() {
		return maletero;
	}

	public void setMaletero(Integer maletero) {
		this.maletero = maletero;
	}
	
	public Integer getKmActuales() {
		return kmActuales;
	}

	public void setKmActuales(Integer kmActuales) {
		this.kmActuales = kmActuales;
	}
	
	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Disponible getDisponible() {
		return disponible;
	}

	public void setDisponible(Disponible disponible) {
		this.disponible = disponible;
	}
	
	public Combustible getCombustible() {
		return combustible;
	}

	public void setCombustible(Combustible combustible) {
		this.combustible = combustible;
	}

	public Concesionario getConcesionario() {
		return concesionario;
	}

	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public SeguroVehiculo getSeguroVehiculo() {
		return seguro_vehiculo;
	}

	public void setSeguroVehiculo(SeguroVehiculo seguroVehiculo) {
		this.seguro_vehiculo = seguroVehiculo;
	}
	
	protected Set<Incidencia> getIncidenciasInternal() {
		if (this.incidencias == null) {
			this.incidencias = new HashSet<>();
		}
		return this.incidencias;
	}

	protected void setIncidenciasInternal(Set<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}

	public List<Incidencia> getIncidencias() {
		List<Incidencia> sortedIncidencias = new ArrayList<>(getIncidenciasInternal());
		PropertyComparator.sort(sortedIncidencias, new MutableSortDefinition("descripcion", true, true));
		return Collections.unmodifiableList(sortedIncidencias);
	}

	public void addIncidencia(Incidencia incidencia) {
		getIncidenciasInternal().add(incidencia);
		incidencia.setVehiculos(this);
	}
	
	public boolean removeIncidencia(Incidencia incidencia) {
		return getIncidenciasInternal().remove(incidencia);
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

	public void addVenta(Venta venta) {
		getVentasInternal().add(venta);
		venta.setVehiculo(this);
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
		alquiler.setVehiculo(this);
	}
	
	public boolean removeAlquiler(Alquiler alquiler) {
		return getAlquileresInternal().remove(alquiler);
	}
	

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("matricula", matricula);
		builder.append("precioAlquiler", precioAlquiler);
		builder.append("precioVenta", precioVenta);
		builder.append("marca", marca);
		builder.append("modelo", modelo);
		builder.append("plazas", plazas);
		builder.append("cambio", cambio);
		builder.append("maletero", maletero);
		builder.append("kmActuales", kmActuales);
		builder.append("caracteristicas", caracteristicas);
		builder.append("estado", estado);
		builder.append("disponible", disponible);
		builder.append("combustible", combustible);
		builder.append("concesionario", concesionario);
		builder.append("oferta", oferta);
		builder.append("seguro_vehiculo", seguro_vehiculo);
		builder.append("id", id);
		builder.append("getMatricula()", getMatricula());
		builder.append("getPrecioAlquiler()", getPrecioAlquiler());
		builder.append("getPrecioVenta()", getPrecioVenta());
		builder.append("getMarca()", getMarca());
		builder.append("getModelo()", getModelo());
		builder.append("getPlazas()", getPlazas());
		builder.append("getCambio()", getCambio());
		builder.append("getMaletero()", getMaletero());
		builder.append("getKmActuales()", getKmActuales());
		builder.append("getCaracteristicas()", getCaracteristicas());
		builder.append("getEstado()", getEstado());
		builder.append("getDisponible()", getDisponible());
		builder.append("getCombustible()", getCombustible());
		builder.append("getConcesionario()", getConcesionario());
		builder.append("getOferta()", getOferta());
		builder.append("getSeguroVehiculo()", getSeguroVehiculo());
		builder.append("getId()", getId());
		builder.append("isNew()", isNew());
		return builder.toString();
	}
	
	

}
