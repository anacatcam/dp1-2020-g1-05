package com.springframework.samples.madaja.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.util.EntityUtils;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SearchServiceTests {

	@Autowired
	protected SearchService searchService;
	
	@Test
	void shouldSearchConcesionarios() {
		List<Concesionario> concesionarios = this.searchService.searchConcesionarios("Systasa");
		Concesionario concesionario = EntityUtils.getById(concesionarios, Concesionario.class, 1);

		assertThat(concesionario.getNombre()).isEqualTo("Systasa");
	}
	
	@Test
	void shouldSearchClientes() {
		List<Cliente> clientes = this.searchService.searchClientes("Manuel");
		Cliente cliente = EntityUtils.getById(clientes, Cliente.class, 1);
		
		assertThat(cliente.getFirstName()).isEqualTo("Manuel");
	}
	
	@Test
	void shouldSearchVehiculos() {
		List<Vehiculos> vehiculos = this.searchService.searchVehiculos("Renault");
		Vehiculos vehiculo = EntityUtils.getById(vehiculos, Vehiculos.class, 3);
		
		assertThat(vehiculo.getMarca()).isEqualTo("Renault");
	}	
}
