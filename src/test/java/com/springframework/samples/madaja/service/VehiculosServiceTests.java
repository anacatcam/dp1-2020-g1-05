package com.springframework.samples.madaja.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Cambio;
import com.springframework.samples.madaja.model.Combustible;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.repository.CambioRepository;
import com.springframework.samples.madaja.repository.CombustibleRepository;
import com.springframework.samples.madaja.repository.ConcesionarioRepository;
import com.springframework.samples.madaja.repository.DisponibleRepository;
import com.springframework.samples.madaja.repository.SeguroVehiculoRepository;
import com.springframework.samples.madaja.repository.VehiculosRepository;
import com.springframework.samples.madaja.util.EntityUtils;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VehiculosServiceTests {
	
	@Mock
	private VehiculosRepository vehiculosRepository;
	
	@Mock
	private CambioRepository cambioRepository;
	
	@Mock
	private ConcesionarioRepository concesionarioRepository;
	
	@Mock
	private CombustibleRepository combustibleRepository;
	
	@Mock
	private DisponibleRepository disponibleRepository;
	
	@Mock
	private SeguroVehiculoRepository seguroVehiculoRepository;
	
	@Autowired
	protected VehiculosService vehiculosService;
	
	private Disponible disponible;
	private Oferta oferta;
	private Cambio cambio;
	private Combustible combustible;
	private Concesionario concesionario;
	private SeguroVehiculo seguroVehiculo;	
	private Vehiculos vehiculo;
	
	@BeforeEach
	void setUp() {
		vehiculosService=new VehiculosService(vehiculosRepository, cambioRepository, concesionarioRepository, combustibleRepository, disponibleRepository, seguroVehiculoRepository);
		
		combustible = new Combustible();
		combustible.setId(1);
		combustible.setName("diesel");
		
	    cambio = new Cambio();
		cambio.setId(1);
		cambio.setName("automático");
		
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
		disponible.setId(3);
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
	}
		
	
	@Test
	void testSaveVehiculo() throws Exception{
		vehiculosService.saveVehiculo(vehiculo);
		verify(vehiculosRepository).save(vehiculo);
	}
	
	@Test
	void testDeleteVehiculo() throws Exception{
		vehiculosService.deleteVehiculo(vehiculo);
		verify(vehiculosRepository).delete(vehiculo);
	}
	
	@Test
	void testFindVehiculoById() throws Exception{
		when(vehiculosRepository.findById(anyInt())).thenReturn(vehiculo);
		
		vehiculosService.findVehiculoById(anyInt());
		
		verify(vehiculosRepository).findById(anyInt());
		assertEquals(vehiculo, vehiculosService.findVehiculoById(anyInt()));
	}
	
	@Test
	void testFindDisponibleById()  throws Exception{
		when(disponibleRepository.findById(anyInt())).thenReturn(disponible);
		
		vehiculosService.findDisponibleById(anyInt());
		
		verify(disponibleRepository).findById(anyInt());
		assertEquals(disponible, vehiculosService.findDisponibleById(anyInt()));
	}
	
	@Test
	void testFindByMatricula() throws Exception{
		when(vehiculosRepository.findByMatricula(anyString())).thenReturn(vehiculo);	
		
		vehiculosService.findByMatricula(anyString());
		
		verify(vehiculosRepository).findByMatricula(anyString());
		assertEquals(vehiculo, vehiculosService.findByMatricula(anyString()));
	}

	@Test
	void testFindByOferta() throws Exception{
		List<Vehiculos> vehiculos= new ArrayList<Vehiculos>();
		vehiculos.add(vehiculo);
		when(vehiculosRepository.findByOferta(anyInt())).thenReturn(vehiculos);	
		
		vehiculosService.findByOferta(anyInt());
		
		verify(vehiculosRepository).findByOferta(anyInt());
		assertEquals(vehiculos, vehiculosService.findByOferta(anyInt()));
	}
	
	@Test
	void testFindByDisponible() throws Exception{
		List<Vehiculos> vehiculos= new ArrayList<Vehiculos>();
		vehiculos.add(vehiculo);
		when(vehiculosRepository.findByDisponible(anyInt())).thenReturn(vehiculos);
		
		vehiculosService.findByDisponible(anyInt());
		
		verify(vehiculosRepository).findByDisponible(anyInt());
		assertEquals(vehiculos, vehiculosService.findByDisponible(anyInt()));
	}
	
	@Test
	void testFindAll() throws Exception{
		List<Vehiculos> vehiculos= new ArrayList<Vehiculos>();
		vehiculos.add(vehiculo);
		when(vehiculosRepository.findAllByDisponible()).thenReturn(vehiculos);
		
		vehiculosService.findAllVehiculos();

		verify(vehiculosRepository).findAllByDisponible();
		assertEquals(vehiculos, vehiculosService.findAllVehiculos());
				
	}
	
	@Test
	void testFindAllVehiculosDisponiblesYsinOfertas() throws Exception{
		List<Vehiculos> vehiculos= new ArrayList<Vehiculos>();
		vehiculos.add(vehiculo);
		when(vehiculosRepository.findAllVehiculosDisponiblesYsinOfertas()).thenReturn(vehiculos);	
		
		vehiculosService.findAllVehiculosDisponiblesYsinOfertas();
		
		verify(vehiculosRepository).findAllVehiculosDisponiblesYsinOfertas();
		assertEquals(vehiculos, vehiculosService.findAllVehiculosDisponiblesYsinOfertas());
	}
	
	@Test
	void testFindAllCambios() throws Exception{
		List<Cambio> cambios=new ArrayList<Cambio>();
		cambios.add(cambio);
		when(cambioRepository.findAll()).thenReturn(cambios);
		
		vehiculosService.findAllCambios();
		
		verify(cambioRepository).findAll();
		assertEquals(cambios, vehiculosService.findAllCambios());
		
	}
	
	@Test
	void testFindAllConcesionarios() throws Exception{
		List<Concesionario> concesionarios=new ArrayList<Concesionario>();
		concesionarios.add(concesionario);
		when(concesionarioRepository.findAll()).thenReturn(concesionarios);
		
		vehiculosService.findAllConcesionarios();
		
		verify(concesionarioRepository).findAll();
		assertEquals(concesionarios, vehiculosService.findAllConcesionarios());
		
	}
	
	@Test
	void testfindAllCombustibles() throws Exception{
		List<Combustible> combustibles=new ArrayList<Combustible>();
		combustibles.add(combustible);
		when(combustibleRepository.findAll()).thenReturn(combustibles);
		
		vehiculosService.findAllCombustibles();
		
		verify(combustibleRepository).findAll();
		assertEquals(combustibles, vehiculosService.findAllCombustibles());
		
	}
	
	@Test
	void testFindAllDisponibles() throws Exception{
		List<Disponible> disponibles=new ArrayList<Disponible>();
		disponibles.add(disponible);
		when(disponibleRepository.findAll()).thenReturn(disponibles);
		
		vehiculosService.findAllDisponibles();
		
		verify(disponibleRepository).findAll();
		assertEquals(disponibles, vehiculosService.findAllDisponibles());
		
	}
	
	@Test
	void testFindAllSeguros() throws Exception{
		List<SeguroVehiculo> seguros=new ArrayList<SeguroVehiculo>();
		seguros.add(seguroVehiculo);
		when(seguroVehiculoRepository.findAll()).thenReturn(seguros);
		
		vehiculosService.findAllSeguros();
		
		verify(seguroVehiculoRepository).findAll();
		assertEquals(seguros, vehiculosService.findAllSeguros());
		
	}
	
}
