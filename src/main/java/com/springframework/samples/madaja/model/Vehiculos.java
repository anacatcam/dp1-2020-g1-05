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
	
	@Column(name = "plazas")
	private Integer plazas;
	
	@ManyToOne
	@JoinColumn(name = "cambio_id")
	private Cambio cambio;
	
	@ManyToOne
	@JoinColumn(name = "maletero_id")
	private Maletero maletero;
	
	@Column(name = "caracteristicas")
	@NotEmpty
	private String caracteristicas;
	
	@Column(name = "disponible")
	private Boolean disponible;
	
	@Column(name = "alquilado")
	private Boolean alquilado;
	
	@Column(name = "vendido")
	private Boolean vendido;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private Concesionario concesionario;	
	
	@OneToOne
	@JoinColumn(name = "oferta_id")
	private Oferta oferta;
	
	@OneToOne
	@JoinColumn(name = "seguro_vehiculo_id", unique = true, nullable = false)
	private SeguroVehiculo seguroVehiculo;

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

	public Maletero getMaletero() {
		return maletero;
	}

	public void setMaletero(Maletero maletero) {
		this.maletero = maletero;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Boolean getAlquilado() {
		return alquilado;
	}

	public void setAlquilado(Boolean alquilado) {
		this.alquilado = alquilado;
	}

	public Boolean getVendido() {
		return vendido;
	}

	public void setVendido(Boolean vendido) {
		this.vendido = vendido;
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
		return seguroVehiculo;
	}

	public void setSeguroVehiculo(SeguroVehiculo seguroVehiculo) {
		this.seguroVehiculo = seguroVehiculo;
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
		builder.append("caracteristicas", caracteristicas);
		builder.append("disponible", disponible);
		builder.append("alquilado", alquilado);
		builder.append("vendido", vendido);
		builder.append("concesionario", concesionario);
		builder.append("oferta", oferta);
		builder.append("seguroVehiculo", seguroVehiculo);
		builder.append("id", id);
		builder.append("getMatricula()", getMatricula());
		builder.append("getPrecioAlquiler()", getPrecioAlquiler());
		builder.append("getPrecioVenta()", getPrecioVenta());
		builder.append("getMarca()", getMarca());
		builder.append("getModelo()", getModelo());
		builder.append("getPlazas()", getPlazas());
		builder.append("getCambio()", getCambio());
		builder.append("getMaletero()", getMaletero());
		builder.append("getCaracteristicas()", getCaracteristicas());
		builder.append("getDisponible()", getDisponible());
		builder.append("getAlquilado()", getAlquilado());
		builder.append("getVendido()", getVendido());
		builder.append("getConcesionario()", getConcesionario());
		builder.append("getOferta()", getOferta());
		builder.append("getSeguroVehiculo()", getSeguroVehiculo());
		builder.append("getId()", getId());
		builder.append("isNew()", isNew());
		builder.append("getClass()", getClass());
		builder.append("hashCode()", hashCode());
		builder.append("toString()", super.toString());
		return builder.toString();
	}
	
	

}
