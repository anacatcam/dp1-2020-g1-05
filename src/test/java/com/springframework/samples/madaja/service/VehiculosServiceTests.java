package com.springframework.samples.madaja.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.util.EntityUtils;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VehiculosServiceTests {

	@Autowired
	protected VehiculosService vehiculosService;
	
	@Test
	void shouldFindAllDisponible() {
		Collection<Disponible> disponibles = this.vehiculosService.findAllDisponibles();
		
		Disponible disp = EntityUtils.getById(disponibles, Disponible.class, 1);
		Disponible disp2 = EntityUtils.getById(disponibles, Disponible.class, 2);
		Disponible disp3 = EntityUtils.getById(disponibles, Disponible.class, 3);
	
		assertThat(disp.getName()).isEqualTo("alquiler");
		assertThat(disp2.getName()).isEqualTo("venta");
		assertThat(disp3.getName()).isEqualTo("alquiler o venta");
	}
	
	@Test
	void shouldFindByDisponible() {
		Collection<Vehiculos> vehiculos = this.vehiculosService.findByDisponible(1);

		Vehiculos vehiculo = EntityUtils.getById(vehiculos, Vehiculos.class, 2);
		
		assertThat(vehiculo.getMatricula()).isEqualTo("2341 EXH");
	}
}
