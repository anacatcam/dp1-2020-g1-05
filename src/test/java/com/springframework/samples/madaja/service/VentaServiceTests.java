package com.springframework.samples.madaja.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springframework.samples.madaja.model.Cambio;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Combustible;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.Reserva;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.User;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.repository.VentaRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class VentaServiceTests {
	
	@Mock
	private VentaRepository ventaRepository;
	
	@Autowired
	protected VentaService ventaService;
	
	private User usuario;
	private Cliente cliente;
	private Venta venta;
	private Reserva reserva;
	private Disponible disponible;
	private Oferta oferta;
	private Cambio cambio;
	private Combustible combustible;
	private Concesionario concesionario;
	private SeguroVehiculo seguroVehiculo;
	private Vehiculos vehiculo;
	
	
	@BeforeEach 
	void setUp() {
		ventaService= new VentaService(ventaRepository);
		
		usuario = new User();
		usuario.setUsername("alejandro");
		usuario.setEnabled(Boolean.TRUE);
		usuario.setPassword("contraseña3");
		
		cliente = new Cliente();
		cliente.setId(1);
		cliente.setFirstName("Alejandro");
		cliente.setLastName("Castellano Sanz");
		cliente.setDni("12422051G");
		cliente.setEmail("alejcastz@gmail.co");
		cliente.setEsConflictivo("No lo es");
		cliente.setTelefono("637666517");
		cliente.setUser(usuario);
		
		reserva = new Reserva();
		reserva.setId(1);
		reserva.setFechaGastos(LocalDate.of(2020, 9, 3));
		reserva.setFianza(317.8);
		reserva.setCliente(cliente);
		
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
		
		venta=new Venta();
		venta.setId(1);
		venta.setCliente(cliente);
		venta.setFecha(LocalDate.of(2021, 12, 27));
		venta.setReserva(reserva);
		venta.setVehiculo(vehiculo);
	}
	
	@Test
	void testFindVentaById() throws Exception {
		when(ventaRepository.findById(anyInt())).thenReturn(venta);
		
		ventaService.findVentaById(anyInt());
		
		verify(ventaRepository).findById(anyInt());
		assertEquals(venta, ventaService.findVentaById(anyInt()));
	}
	

	@Test
	void testFindAllVentas() throws Exception{
		List<Venta> ventas=new ArrayList<Venta>();
		ventas.add(venta);
		Iterable<Venta> ventasIt=ventas;
		when(ventaRepository.findAll()).thenReturn(ventasIt);
		
		ventaService.findAllVentas();
		
		verify(ventaRepository).findAll();
		assertEquals(ventasIt, ventaService.findAllVentas());
	}
	
	@Test
	void testFindVentasByDni() throws Exception{
		List<Venta> ventas=new ArrayList<Venta>();
		ventas.add(venta);
		when(ventaRepository.findByDniCliente(anyString())).thenReturn(ventas);
		
		ventaService.findVentasByDni(anyString());
		
		verify(ventaRepository).findByDniCliente(anyString());
		assertEquals(ventas, ventaService.findVentasByDni(anyString()));
	}
	
	@Test
	void testSaveVenta() throws Exception{
		this.ventaService.saveVenta(venta);
		
		verify(ventaRepository).save(venta);
  }
  
  @Test
  void testFindVentaByEnvio() throws Exception{
		when(ventaRepository.findByEnvio(anyInt())).thenReturn(venta);
		
		ventaService.findVentaByEnvio(anyInt());
		
		verify(ventaRepository).findByEnvio(anyInt());
		assertEquals(venta,ventaService.findVentaByEnvio(anyInt()));
	}
  
  //PAGINACIÓN
  @Test
  void testGetAll() throws Exception{
	  List<Venta> ventas=new ArrayList<Venta>();
	  ventas.add(venta);
	  
	  String dni =cliente.getDni();
	  
	  Pageable pageable = PageRequest.of(0, 8);
	  Page<Venta> page = new PageImpl<Venta>(ventas);
	 
	  when(ventaRepository.findAll(dni, pageable)).thenReturn(page);
		
	  ventaService.getAll(dni,pageable);
		
	  verify(ventaRepository).findAll(dni, pageable);
	  assertEquals(page, ventaService.getAll(dni, pageable));
  }
}
