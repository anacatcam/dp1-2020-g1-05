package com.springframework.samples.madaja.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Venta;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VentaServiceTests {

	@Autowired
	protected VentaService ventaService;
	
	@Test
	void shouldFindVentaById() {
		Venta venta = this.ventaService.findVentaById(1);
		assertThat(venta.getCliente().getDni()).isEqualTo("15442604");
	}
	
	@Test
	void shouldFindAllVentas() {
		Iterable<Venta> ventas = this.ventaService.findAllVentas();
		Iterator<Venta> venta_it = ventas.iterator();
		Boolean encontrado = false;
		while(venta_it.hasNext()) {
			Venta venta = venta_it.next();
			if(venta.getId() == 1) {
				encontrado = true;
				break;
			}
		}
		assertThat(encontrado).isTrue();
	}
	
	@Test
	void shouldFindVentasByDni() {
		List<Venta> ventas = this.ventaService.findVentasByDni("15442604");
		assertThat(ventas.size()).isEqualTo(1);
	}
}
