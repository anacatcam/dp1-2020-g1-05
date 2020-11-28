package com.springframework.samples.madaja.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;


import com.springframework.samples.madaja.model.Incidencia;

import com.springframework.samples.madaja.model.Vehiculos;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class IncidenciaServiceTests {
	

	@Autowired
	protected IncidenciaService incidenciaService;
	
	@Test
	void shouldSaveIncidencia() {
		Collection<Incidencia> incidencias = this.incidenciaService.findAllIncidencia();
		int encontrado= incidencias.size();
		Incidencia incidencia=new Incidencia();
		Vehiculos vehiculo=new Vehiculos();
		vehiculo.setCaracteristicas("Fiabilidad, espacioso");
		vehiculo.setEstado("Cómo nuevo");
		vehiculo.setKmActuales(15000);
		vehiculo.setMaletero(300);
		vehiculo.setMarca("BMW");
		vehiculo.setMatricula("7523 DPX");
		vehiculo.setModelo("MX6");
		vehiculo.setPlazas(5);
		vehiculo.setPrecioAlquiler(600);
		vehiculo.setPrecioVenta(15000);
		vehiculo.setPuertas(4);
//		/Cambio cambio=new Cambio();   //REVISAR ENUMERADOS IMPORTANTE
//		vehiculo.setCambio(cambio);
//		vehiculo.setCombustible(combustible);
//		vehiculo.setConcesionario(1);
//		vehiculo.setDisponible(2);
//		vehiculo.setSeguroVehiculo();
		
		incidencia.setDescripcion("Pinchazo en rueda frontal izquierda");
		incidencia.setSolucionada(true);
		incidencia.setVehiculos(vehiculo);
		
		this.incidenciaService.saveIncidencia(incidencia);
		assertNotEquals(incidencia.getId().longValue(),0);
		
		incidencias=this.incidenciaService.findAllIncidencia();
		assertEquals(incidencias.size(), encontrado+1);
	}
	
	@Test  
	void shouldFindIncidenciaById() {
		Incidencia incidencia = this.incidenciaService.findIncidenciaById(5);
		assertFalse(incidencia.getDescripcion().isEmpty());
	}
	
	@Test
	void shouldFindAllIncidencias() {
		Iterable<Incidencia> incidencias = this.incidenciaService.findAllIncidencia();
		Iterator<Incidencia> incidencia_it= incidencias.iterator();
		Boolean encontrado = false;
		while(incidencia_it.hasNext()) {
			Incidencia incidencia= incidencia_it.next();
			if(incidencia.getId() == 1) {
				encontrado = true;
				break;
			}
		}
		assertTrue(encontrado);
	}

}


