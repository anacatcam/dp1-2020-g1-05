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
	@JoinColumn(name = "concesionario_id")
	private Concesionario concesionario;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculos")
	private Set<Incidencia> incidencias;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculos")
	private Set<Oferta> ofertas;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculos")
	private Set<ContratoAlquiler> contratos_alquileres;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "vehiculos")
	private ContratoVenta contratoVenta;
	
	@OneToOne
	@JoinColumn(name = "seguro_vehiculo_id", unique = true)
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

	public void setCambio(Cambio tipoCambio, String name) {
		tipoCambio.setName(name);
		this.cambio = tipoCambio;
	}

	public Maletero getMaletero() {
		return maletero;
	}

	public void setMaletero(Maletero tipoMaletero, String name) {
		tipoMaletero.setName(name);
		this.maletero = tipoMaletero;
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
		incidencia.setVehiculo(this);
	}
	
	public boolean removeIncidencia(Incidencia incidencia) {
		return getIncidenciasInternal().remove(incidencia);
	}
	
	protected Set<Oferta> getOfertasInternal() {
		if (this.ofertas == null) {
			this.ofertas = new HashSet<>();
		}
		return this.ofertas;
	}

	protected void setOfertasInternal(Set<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	public List<Oferta> getOfertas() {
		List<Oferta> sortedOfertas = new ArrayList<>(getOfertasInternal());
		PropertyComparator.sort(sortedOfertas, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedOfertas);
	}

	public Set<ContratoAlquiler> getContratos_alquileres() {
		return contratos_alquileres;
	}

	public void setContratos_alquileres(Set<ContratoAlquiler> contratos_alquileres) {
		this.contratos_alquileres = contratos_alquileres;
	}

	public ContratoVenta getContratos_ventas() {
		return contratoVenta;
	}

	public void setContratos_ventas(ContratoVenta contratos_ventas) {
		this.contratoVenta = contratos_ventas;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
				
				.append("id", this.getId()).append("new", this.isNew()).append(matricula, this.matricula)
				.append(precioAlquiler.toString(), this.precioAlquiler).append(precioVenta.toString(), this.precioVenta)
				.append(marca, this.marca).append(modelo, this.modelo).append(plazas.toString(), this.plazas)
				.append(cambio.toString(), this.cambio).append(maletero.toString(), this.maletero)
				.append(caracteristicas, this.caracteristicas).append(disponible.toString(), this.disponible)
				.append(alquilado.toString(), this.alquilado).append(vendido.toString(), this.vendido)
				.append(concesionario.toString(), this.concesionario)
				.append(contratoVenta.toString(), this.contratoVenta)
				.append(contratos_alquileres.toString(), this.contratos_alquileres)
				.toString();
	
	}

}
