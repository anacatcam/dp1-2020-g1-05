package com.springframework.samples.madaja.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "vehiculos")
public class Vehiculos extends BaseEntity{ //Pendiente de añadir Set<Incidencias> Incidencias y Concesionario Concesionario
	
	@Column(name = "matricula")
	@NotEmpty
	private String matricula;
	
	@Column(name = "precio_alquiler")
	@NotEmpty
	private Integer precioAlquiler;
	
	@Column(name = "precio_venta")
	@NotEmpty
	private Integer precioVenta;
	
	@Column(name = "marca")
	@NotEmpty
	private String marca;
	
	@Column(name = "modelo")
	@NotEmpty
	private String modelo;
	
	@Column(name = "plazas")
	@NotEmpty
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

	public void setCambio(Cambio tipoCambio) {
		this.cambio = tipoCambio;
	}

	public Maletero getMaletero() {
		return maletero;
	}

	public void setMaletero(Maletero tipoMaletero) {
		this.maletero = tipoMaletero;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
				
				.append("id", this.getId()).append("new", this.isNew()).append(matricula, this.matricula)
				.append(precioAlquiler.toString(), this.precioAlquiler).append(precioVenta.toString(), this.precioVenta)
				.append(marca, this.marca).append(modelo, this.modelo).append(plazas.toString(), this.plazas)
				.append(cambio.toString(), this.cambio).append(maletero.toString(), this.maletero)
				.append(caracteristicas, this.caracteristicas).toString();
	
	}

}
