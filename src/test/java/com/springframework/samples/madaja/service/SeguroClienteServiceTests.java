package com.springframework.samples.madaja.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.springframework.samples.madaja.model.Cambio;
import com.springframework.samples.madaja.model.Combustible;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.SeguroCliente;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.repository.SeguroClienteRepository;

@ExtendWith(MockitoExtension.class)
public class SeguroClienteServiceTests {
	

	@Autowired
	protected SeguroClienteService seguroClienteService;
	
	@Mock
	private SeguroClienteRepository seguroClienteRepository;
	
	
	private SeguroVehiculo seguroVehiculo;
	
	private Oferta oferta;
	
	private Disponible disponible;
	
	private Concesionario concesionario;
	
	private Combustible combustible;
	
	private Cambio cambio;
	
	private Vehiculos vehiculo;
	
	private SeguroCliente seguroCliente;
	
	@BeforeEach
	void setUp() {
		this.seguroClienteService = new SeguroClienteService(seguroClienteRepository);
		seguroVehiculo = new SeguroVehiculo();
		seguroVehiculo.setId(1);
		seguroVehiculo.setNumeroPoliza("32151");
		seguroVehiculo.setPrecio(450.32);
		seguroVehiculo.setFranquicia(200);
		seguroVehiculo.setCobertura("A todo riesgo");
		seguroVehiculo.setFechaInicio(LocalDate.of(2020, 9, 07));
		seguroVehiculo.setFechaFin(LocalDate.of(2021, 9, 07));
		
		oferta = new Oferta();
		oferta.setId(1);
		oferta.setName("Oferta 1");
		oferta.setDescuento(22.0);
		oferta.setFechaLimite(LocalDate.of(2020, 6, 12));
		oferta.setHoraLimite(LocalTime.of(2, 3, 4));
		
		disponible = new Disponible();
		disponible.setId(1);
		disponible.setName("venta");
		
		concesionario = new Concesionario();
		concesionario.setId(1);
		concesionario.setCodigoPostal("41063");
		concesionario.setDireccion("Calle Los Pacos");
		concesionario.setEmail("concesionario1@gmail.com");
		concesionario.setLocalidad("Lora del Río");
		concesionario.setProvincia("Sevilla");
		concesionario.setPais("España");
		concesionario.setTelefono("608555102");
		
		combustible = new Combustible();
		combustible.setId(1);
		combustible.setName("diesel");
		
	    cambio = new Cambio();
		cambio.setId(1);
		cambio.setName("automático");
		
		vehiculo = new Vehiculos();
		vehiculo.setId(1);
		vehiculo.setCaracteristicas("Espacioso / Amplio");
		vehiculo.setEstado("Nuevo");
		vehiculo.setKmActuales(1000);
		vehiculo.setMaletero(100);
		vehiculo.setMarca("Lamborghini");
		vehiculo.setMatricula("6874 KJU");
		vehiculo.setModelo("Gallardo");
		vehiculo.setPlazas(4);
		vehiculo.setPrecioAlquiler(432);
		vehiculo.setPrecioVenta(13000);
		vehiculo.setPuertas(2);
		vehiculo.setCambio(cambio);
		vehiculo.setCombustible(combustible);
		vehiculo.setConcesionario(concesionario);
		vehiculo.setDisponible(disponible);
		vehiculo.setOferta(oferta);
		vehiculo.setSeguroVehiculo(seguroVehiculo);
		
		seguroCliente = new SeguroCliente();
		seguroCliente.setCobertura("Hola");
		seguroCliente.setFranquicia(150);
		seguroCliente.setFechaInicio(LocalDate.of(2020, 12, 10));
		seguroCliente.setFechaFin(LocalDate.of(2020, 12, 15));
		seguroCliente.setPrecio(390.00);
		seguroCliente.setVehiculos(vehiculo);
	}
	
	
	@Test
	void testSaveSeguroCliente() throws Exception{
		this.seguroClienteService.saveSeguroCliente(seguroCliente);
		
		verify(seguroClienteRepository).save(seguroCliente);
	}
	
	@Test  
	void testFindSeguroClienteById() throws Exception{
		when(seguroClienteRepository.findById(anyInt())).thenReturn(seguroCliente);
		
		seguroClienteService.findSeguroClienteById(anyInt());
		
		verify(seguroClienteRepository).findById(anyInt());
		assertEquals(seguroCliente, seguroClienteService.findSeguroClienteById(anyInt()));
	}
	/*
	@Test
	void testFindAllSegurosCliente() throws Exception{
		List<SeguroCliente> segurosCliente = new ArrayList<SeguroCliente>();
		segurosCliente.add(seguroCliente);
		when(seguroClienteRepository.findAll()).thenReturn(segurosCliente);
		
		seguroClienteService.findAllSeguroCliente();
		
		verify(seguroClienteRepository).findAll();
		assertEquals(segurosCliente, seguroClienteService.findAllSeguroCliente());
	}*/
	
	@Test
	public void testDeleteById() throws Exception {
		seguroClienteService.deleteById(anyInt());
		verify(seguroClienteRepository).deleteById(anyInt());
	}
	
}


