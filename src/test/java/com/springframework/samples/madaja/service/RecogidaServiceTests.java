//package com.springframework.samples.madaja.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.verify;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.springframework.samples.madaja.model.Cambio;
//import com.springframework.samples.madaja.model.Combustible;
//import com.springframework.samples.madaja.model.Concesionario;
//import com.springframework.samples.madaja.model.Disponible;
//import com.springframework.samples.madaja.model.Mecanico;
//import com.springframework.samples.madaja.model.Oferta;
//import com.springframework.samples.madaja.model.Recogida;
//import com.springframework.samples.madaja.model.SeguroVehiculo;
//import com.springframework.samples.madaja.model.Vehiculos;
//import com.springframework.samples.madaja.repository.RecogidaRepository;
//
//@ExtendWith(MockitoExtension.class)
//public class RecogidaServiceTests {
//
//	@Mock
//	private RecogidaRepository recogidaRepository;
//
//	@Autowired
//	protected RecogidaService recogidaService;
//
//	private Mecanico mecanico;
//
//	private Recogida recogida;
//
//	private Vehiculos vehiculo;
//
//	private Cambio cambio;
//
//	private Combustible combustible;
//
//	private Concesionario concesionario;
//
//	private Disponible disponible;
//
//	private Oferta oferta;
//
//	private SeguroVehiculo seguroVehiculo;
//
//	@BeforeEach
//	void setUp() {
//		recogidaService = new RecogidaService(recogidaRepository);
//
//		seguroVehiculo = new SeguroVehiculo();
//		seguroVehiculo.setId(1);
//		seguroVehiculo.setNumeroPoliza("32151");
//		seguroVehiculo.setPrecio(450.32);
//		seguroVehiculo.setFranquicia(200);
//		seguroVehiculo.setCobertura("A todo riesgo");
//		seguroVehiculo.setFechaInicio(LocalDate.of(2020, 9, 07));
//		seguroVehiculo.setFechaFin(LocalDate.of(2021, 9, 07));
//
//		oferta = new Oferta();
//		oferta.setId(1);
//		oferta.setName("Oferta 1");
//		oferta.setDescuento(22.0);
//		oferta.setFechaLimite(LocalDate.of(2020, 6, 12));
//		oferta.setHoraLimite(LocalTime.of(2, 3, 4));
//
//		disponible = new Disponible();
//		disponible.setId(1);
//		disponible.setName("venta");
//
//		concesionario = new Concesionario();
//		concesionario.setId(1);
//		concesionario.setCodigoPostal("41063");
//		concesionario.setDireccion("Calle Los Pacos");
//		concesionario.setEmail("concesionario1@gmail.com");
//		concesionario.setLocalidad("Lora del Río");
//		concesionario.setProvincia("Sevilla");
//		concesionario.setPais("España");
//		concesionario.setTelefono("608555102");
//
//		combustible = new Combustible();
//		combustible.setId(1);
//		combustible.setName("diesel");
//
//		cambio = new Cambio();
//		cambio.setId(1);
//		cambio.setName("automático");
//
//		vehiculo = new Vehiculos();
//		vehiculo.setId(1);
//		vehiculo.setCaracteristicas("Espacioso / Amplio");
//		vehiculo.setEstado("Nuevo");
//		vehiculo.setKmActuales(1000);
//		vehiculo.setMaletero(100);
//		vehiculo.setMarca("Lamborghini");
//		vehiculo.setMatricula("6874 KJU");
//		vehiculo.setModelo("Gallardo");
//		vehiculo.setPlazas(4);
//		vehiculo.setPrecioAlquiler(432);
//		vehiculo.setPrecioVenta(13000);
//		vehiculo.setPuertas(2);
//		vehiculo.setCambio(cambio);
//		vehiculo.setCombustible(combustible);
//		vehiculo.setConcesionario(concesionario);
//		vehiculo.setDisponible(disponible);
//		vehiculo.setOferta(oferta);
//		vehiculo.setSeguroVehiculo(seguroVehiculo);
//
//		mecanico = new Mecanico();
//		mecanico.setDni("47565973E");
//		mecanico.setApellidos("Molinas Trujillo");
//		mecanico.setEmail("alvmoltrujillo@gmail.com");
//		mecanico.setNombre("Álvaro");
//		mecanico.setTelefono("625496828");
//		mecanico.setSueldo(1730.0);
//
//		recogida = new Recogida();
//		recogida.setId(1);
//		recogida.setCodigoPostal("41005");
//		recogida.setDireccion("C/Aznalcazar");
//		recogida.setLocalidad("Sevilla");
//		recogida.setPais("España");
//		recogida.setProvincia("Sevilla");
//		recogida.setHora(LocalTime.of(12, 0, 0));
//		recogida.setMecanico(mecanico);
//
//	}
//
//	@Test
//	void testFindAllRecogidas() throws Exception {
//		List<Recogida> recogidas = new ArrayList<Recogida>();
//		recogidas.add(recogida);
//		Iterable<Recogida> recogidasIt = recogidas;
//		when(recogidaRepository.findAll()).thenReturn(recogidasIt);
//
//		recogidaService.findAllRecogidas();
//
//		verify(recogidaRepository).findAll();
//		assertEquals(recogidasIt, recogidaService.findAllRecogidas());
//	}
//
//	@Test
//	void testFindRecogidaById() throws Exception {
//		when(recogidaRepository.findById(anyInt())).thenReturn(recogida);
//
//		recogidaService.findRecogidaById(anyInt());
//
//		verify(recogidaRepository).findById(anyInt());
//		assertEquals(recogida, recogidaService.findRecogidaById(anyInt()));
//	}
//
//	@Test
//	void testSaveRecogida() throws Exception {
//		this.recogidaService.saveRecogida(recogida);
//
//		verify(recogidaRepository).save(recogida);
//	}
//
//}
