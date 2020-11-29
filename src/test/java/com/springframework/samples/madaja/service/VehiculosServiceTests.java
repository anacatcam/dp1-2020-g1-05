package com.springframework.samples.madaja.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Test
	@Transactional
	public void shouldInsertVehiculo() {
		Collection<Vehiculos> vehiculos = this.vehiculosService.findAllVehiculos();
		Disponible alquiler = this.vehiculosService.findDisponibleById(1);
		int found = vehiculos.size();
		
		Vehiculos vehiculo = new Vehiculos();
		vehiculo.setMatricula("1111 AAA");
		vehiculo.setPrecioAlquiler(111);
		vehiculo.setPrecioVenta(50000);
		vehiculo.setMarca("Opel");
		vehiculo.setModelo("Corsa");
		vehiculo.setPuertas(4);
		vehiculo.setPlazas(5);
		vehiculo.setMaletero(1);
		vehiculo.setKmActuales(1000);
		vehiculo.setCaracteristicas("Consume poco");
		vehiculo.setEstado("Seminuevo");
		vehiculo.setDisponible(alquiler);
		
		this.vehiculosService.saveVehiculo(vehiculo);
		assertThat(vehiculo.getId().longValue()).isNotEqualTo(0);
		
		vehiculos = this.vehiculosService.findAllVehiculos();
		assertThat(vehiculos.size()).isEqualTo(found + 1);
	}
	
	@Test
	@Transactional
	public void shouldDeleteVehiculo() {
		Collection<Vehiculos> vehiculosDisponibles = this.vehiculosService.findAllVehiculos();
		Collection<Vehiculos> vehiculosNoDisponibles = this.vehiculosService.findByDisponible(4);
		int foundDisponibles = vehiculosDisponibles.size();
		int foundNoDisponibles = vehiculosNoDisponibles.size();
		
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(1);
		Disponible disponible = this.vehiculosService.findDisponibleById(4);
		vehiculo.setDisponible(disponible);
		
		this.vehiculosService.saveVehiculo(vehiculo);
		assertThat(vehiculo.getId().longValue()).isNotEqualTo(0);
		
		vehiculosDisponibles = this.vehiculosService.findAllVehiculos();
		assertThat(vehiculosDisponibles.size()).isEqualTo(foundDisponibles - 1);
		
		vehiculosNoDisponibles = this.vehiculosService.findByDisponible(4);
		assertThat(vehiculosNoDisponibles.size()).isEqualTo(foundNoDisponibles + 1);

	}
	
	@Test
	@Transactional
	public void shouldUpdateVehiculo() {
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(1);
		String oldMatricula = vehiculo.getMatricula();
		String newMatricula = oldMatricula + "V";
		
		vehiculo.setMatricula(newMatricula);
		this.vehiculosService.saveVehiculo(vehiculo);
		
		vehiculo = this.vehiculosService.findVehiculoById(1);
		assertThat(vehiculo.getMatricula()).isEqualTo(newMatricula);
	}
}
