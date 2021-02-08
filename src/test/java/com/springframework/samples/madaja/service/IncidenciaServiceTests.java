package com.springframework.samples.madaja.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.springframework.samples.madaja.model.Cambio;
import com.springframework.samples.madaja.model.Combustible;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Mecanico;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.repository.IncidenciaRepository;
import com.springframework.samples.madaja.repository.MecanicoRepository;

@ExtendWith(MockitoExtension.class)
public class IncidenciaServiceTests {
	

	@Autowired
	protected IncidenciaService incidenciaService;
	
	@Mock
	private IncidenciaRepository incidenciaRepository;
	
	@Mock
	private MecanicoRepository mecanicoRepository;
	
	private SeguroVehiculo seguroVehiculo;
	
	private Oferta oferta;
	
	private Disponible disponible;
	
	private Concesionario concesionario;
	
	private Combustible combustible;
	
	private Cambio cambio;
	
	private Vehiculos vehiculo;
	
	private Mecanico mecanico;
	
	private Incidencia incidencia;
	@BeforeEach
	void setUp() {
		this.incidenciaService = new IncidenciaService(incidenciaRepository, mecanicoRepository);
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
		
		incidencia = new Incidencia();
		incidencia.setId(1);
		incidencia.setDescripcion("Golpe en la parte frontal");
		incidencia.setSolucionada(Boolean.TRUE);
		incidencia.setVehiculos(vehiculo);
		
		mecanico = new Mecanico();
		mecanico.setDni("47565973E");
		mecanico.setLastName("Molinas Trujillo");
		mecanico.setEmail("alvmoltrujillo@gmail.com");
		mecanico.setFirstName("Álvaro");
		mecanico.setTelefono("625496828");
		mecanico.setSueldo(1730.0);		
	}
	@Test
	void testSaveIncidencia() throws Exception{
		this.incidenciaService.saveIncidencia(incidencia);
		
		verify(incidenciaRepository).save(incidencia);
	}
	
	@Test  
	void testFindIncidenciaById() throws Exception{
		when(incidenciaRepository.findById(anyInt())).thenReturn(incidencia);
		
		incidenciaService.findIncidenciaById(anyInt());
		
		verify(incidenciaRepository).findById(anyInt());
		assertEquals(incidencia, incidenciaService.findIncidenciaById(anyInt()));
	}
	
	@Test
	void testFindAllIncidencias() throws Exception{
		List<Incidencia> incidencias = new ArrayList<Incidencia>();
		incidencias.add(incidencia);
		when(incidenciaRepository.findAll()).thenReturn(incidencias);
		
		incidenciaService.findAllIncidencia();
		
		verify(incidenciaRepository).findAll();
		assertEquals(incidencias, incidenciaService.findAllIncidencia());
	}
	
	@Test
	void testFindAllMecanicos() throws Exception{
		List<Mecanico> mecanicos = new ArrayList<Mecanico>();
		mecanicos.add(mecanico);
		when(mecanicoRepository.findAll()).thenReturn(mecanicos);
		
		incidenciaService.findAllMecanicos();
		
		verify(mecanicoRepository).findAll();
		assertEquals(mecanicos, incidenciaService.findAllMecanicos());
	}
}


