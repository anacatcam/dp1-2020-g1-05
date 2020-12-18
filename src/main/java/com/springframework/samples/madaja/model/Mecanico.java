package com.springframework.samples.madaja.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;


@Entity
@Table(name = "mecanico")
public class Mecanico extends Trabajador{

	@ManyToMany(mappedBy = "mecanicos")
	private Set<Incidencia> incidencias;
	
	@OneToMany(mappedBy = "mecanico", cascade = CascadeType.ALL)
	private Set<Recogida> recogidas;
	
	@OneToMany(mappedBy = "mecanico", cascade = CascadeType.ALL)
	private Set<Envio> envios;

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
	}
	
	public boolean removeIncidencia(Incidencia incidencia) {
		return getIncidenciasInternal().remove(incidencia);
	}
	
	protected Set<Recogida> getRecogidasInternal() {
		if (this.recogidas == null) {
			this.recogidas = new HashSet<>();
		}
		return this.recogidas;
	}

	protected void setRecogidasInternal(Set<Recogida> recogidas) {
		this.recogidas = recogidas;
	}

	public List<Recogida> getRecogidas() {
		List<Recogida> sortedRecogidas = new ArrayList<>(getRecogidasInternal());
		PropertyComparator.sort(sortedRecogidas, new MutableSortDefinition("id", true, true));
		return Collections.unmodifiableList(sortedRecogidas);
	}

	public void addRecogida(Recogida recogida) {
		getRecogidasInternal().add(recogida);
		recogida.setMecanico(this);
	}
	
	public boolean removeRecogida(Recogida recogida) {
		return getRecogidasInternal().remove(recogida);
	}
	
	protected Set<Envio> getEnviosInternal() {
		if (this.envios == null) {
			this.envios = new HashSet<>();
		}
		return this.envios;
	}

	protected void setEnviosInternal(Set<Envio> envios) {
		this.envios = envios;
	}

	public List<Envio> getEnvios() {
		List<Envio> sortedEnvios = new ArrayList<>(getEnviosInternal());
		PropertyComparator.sort(sortedEnvios, new MutableSortDefinition("id", true, true));
		return Collections.unmodifiableList(sortedEnvios);
	}

	public void addEnvio(Envio envio) {
		getEnviosInternal().add(envio);
		envio.setMecanico(this);
	}
	
	public boolean removeEnvio(Envio envio) {
		return getEnviosInternal().remove(envio);
	}

	@Override
	public String toString() {
		ToStringCreator builder = new ToStringCreator(this);
		builder.append("incidencias", incidencias);
		builder.append("sueldo", sueldo);
		builder.append("dni", dni);
		builder.append("nombre", nombre);
		builder.append("apellidos", apellidos);
		builder.append("telefono", telefono);
		builder.append("email", email);
		builder.append("getSueldo()", getSueldo());
		builder.append("getDni()", getDni());
		builder.append("getNombre()", getNombre());
		builder.append("getApellidos()", getApellidos());
		builder.append("getTelefono()", getTelefono());
		builder.append("getEmail()", getEmail());
		return builder.toString();
	}
	
}
