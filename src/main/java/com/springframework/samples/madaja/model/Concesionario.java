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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.hibernate.search.annotations.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "concesionario")
@Indexed
@AnalyzerDef(name = "edgeNGram_query", tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = ASCIIFoldingFilterFactory.class), // Replace accented characeters by their simpler
																	// counterpart (è => e, etc.)
		@TokenFilterDef(factory = LowerCaseFilterFactory.class), // Lowercase all characters
		@TokenFilterDef(factory = StandardFilterFactory.class) })
@AnalyzerDef(name = "edgeNgram", tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = ASCIIFoldingFilterFactory.class), // Replace accented characeters by their simpler
																	// counterpart (è => e, etc.)
		@TokenFilterDef(factory = LowerCaseFilterFactory.class), // Lowercase all characters
		@TokenFilterDef(factory = EdgeNGramFilterFactory.class, // Generate prefix tokens
				params = { @Parameter(name = "minGramSize", value = "1"),
						@Parameter(name = "maxGramSize", value = "10") }) })
public class Concesionario extends Localizacion {

	@Column(name = "nombre")
	@Field(analyzer = @Analyzer(definition = "edgeNgram"))
	@NotEmpty
	private String nombre;

	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;

	@Column(name = "telefono")
	@NotEmpty
	@Length(min = 9, max = 9)
	@Digits(fraction = 0, integer = 10)
	private String telefono;

	@JoinTable(name = "concesionarios_gestores", joinColumns = @JoinColumn(name = "concesionario_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "gestor_id", nullable = false))
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Gestor> gestores;

	@OneToMany(mappedBy = "concesionario", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Vehiculos> vehiculos;

	protected Set<Vehiculos> getVehiculosInternal() {
		if (this.vehiculos == null) {
			this.vehiculos = new HashSet<>();
		}
		return this.vehiculos;
	}

	protected void setVehiculosInternal(Set<Vehiculos> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<Vehiculos> getVehiculos() {
		List<Vehiculos> sortedVehiculos = new ArrayList<>(getVehiculosInternal());
		PropertyComparator.sort(sortedVehiculos, new MutableSortDefinition("id", true, true));
		return Collections.unmodifiableList(sortedVehiculos);
	}

	public void addVehiculo(Vehiculos vehiculos) {
		getVehiculosInternal().add(vehiculos);
		vehiculos.setConcesionario(this);
	}

	public boolean removeVehiculo(Vehiculos vehiculos) {
		return getVehiculosInternal().remove(vehiculos);
	}

	protected Set<Gestor> getGestoresInternal() {
		if (this.gestores == null) {
			this.gestores = new HashSet<>();
		}
		return this.gestores;
	}

	protected void setGestoresInternal(Set<Gestor> gestores) {
		this.gestores = gestores;
	}

	public List<Gestor> getGestores() {
		List<Gestor> sortedGestores = new ArrayList<>(getGestoresInternal());
		PropertyComparator.sort(sortedGestores, new MutableSortDefinition("nombre", true, true));
		return Collections.unmodifiableList(sortedGestores);
	}

	public void addGestor(Gestor gestor) {
		getGestoresInternal().add(gestor);
	}

	public boolean removeGestor(Gestor gestor) {
		return getGestoresInternal().remove(gestor);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Transient
	public String getFullLugar() {
		return localidad + ", " + provincia;
	}

}
