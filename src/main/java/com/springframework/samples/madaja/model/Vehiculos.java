package com.springframework.samples.madaja.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "vehiculos")
public class Vehiculos extends BaseEntity{ //Pendiente de añadir Set<Incidencias> Incidencias y Concesionario Concesionario
	
	@Column(name = "matricula")
	@NotEmpty
	private String matricula;
	
	@Column(name = "precioAlquiler")
	@NotEmpty
	private String precioAlquiler;
	
	@Column(name = "precioVenta")
	@NotEmpty
	private String precioVenta;
	
	@Column(name = "marca")
	@NotEmpty
	private String marca;
	
	@Column(name = "modelo")
	@NotEmpty
	private String modelo;
	
	@Column(name = "plazas")
	@NotEmpty
	private String plazas;
	
	@Column(name = "cambio")
	@NotEmpty
	private String cambio;
	
	@Column(name = "maletero")
	@NotEmpty
	private String maletero;
	
	@Column(name = "caracteristicas")
	@NotEmpty
	private String caracteristicas;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPrecioAlquiler() {
		return precioAlquiler;
	}

	public void setPrecioAlquiler(String precioAlquiler) {
		this.precioAlquiler = precioAlquiler;
	}

	public String getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(String precioVenta) {
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

	public String getPlazas() {
		return plazas;
	}

	public void setPlazas(String plazas) {
		this.plazas = plazas;
	}

	public String getTipoCambio() {
		return cambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.cambio = tipoCambio;
	}

	public String getTipoMaletero() {
		return maletero;
	}

	public void setTipoMaletero(String tipoMaletero) {
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
				.append(cambio, this.cambio).append(maletero, this.maletero)
				.append(caracteristicas, this.caracteristicas).toString();
	
	}

}
