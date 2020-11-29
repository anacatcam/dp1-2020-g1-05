package com.springframework.samples.madaja.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.springframework.samples.madaja.model.Alquiler;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AlquilerServiceTests {

	@Autowired
	protected AlquilerService alquilerService;
	
	@Test
	void shouldFindAlquilerById() {
		Alquiler alquiler = this.alquilerService.findAlquilerById(1); 
		assertThat(alquiler.getCliente().getDni()).isEqualTo("15442604");
	}
	
	@Test
	void shouldFindAlquilerByDni() {
		List<Alquiler> alquileres = this.alquilerService.findAlquilerByDni("15442604");
		assertThat(alquileres.size()).isEqualTo(1);
	} 
	
	@Test
	void shouldFindAlquilerConcretoCliente() {
		Alquiler alquiler = this.alquilerService.findAlquilerConcretoCliente("15442604", "4685 ADT");
		assertThat(alquiler.getId()).isEqualTo(1);
	}
	
	@Test
	void shouldFindAllAlquiler() {
		Iterable<Alquiler> alquileres = this.alquilerService.findAllAlquiler();
		Iterator<Alquiler> alquiler_it = alquileres.iterator();
		Boolean encontrado = false;
		while(alquiler_it.hasNext()) {
			Alquiler alquiler = alquiler_it.next();
			if(alquiler.getId() == 1) {
				encontrado = true;
				break;
			}
		}
		assertThat(encontrado).isTrue();
	}
}
