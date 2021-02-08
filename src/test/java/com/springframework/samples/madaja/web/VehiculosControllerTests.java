package com.springframework.samples.madaja.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import com.springframework.samples.madaja.configuration.SecurityConfiguration;
import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cambio;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Combustible;
import com.springframework.samples.madaja.model.Compania;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Envio;
import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Mecanico;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.Recogida;
import com.springframework.samples.madaja.model.Reserva;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.User;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.SearchService;
import com.springframework.samples.madaja.service.ConcesionarioService;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.web.VehiculosController;

/**
 * Test class for the {@link VehiculosController}
 */

@WebMvcTest(value=VehiculosController.class ,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class VehiculosControllerTests {
	
	
	@Autowired
	private VehiculosController vehiculosController;
	
	@MockBean
	private VehiculosService vehiculosService;
	
	@MockBean
	private SearchService searchService;
	
	@MockBean
	private ConcesionarioService concesionarioService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Cliente cliente;
	
	private User usuario;
	
	private Reserva reserva;
	
	private Vehiculos vehiculo;
	
	private SeguroVehiculo seguroVehiculo;
	
	private Oferta oferta;
	
	private Disponible disponible;
	
	private Concesionario concesionario;
	
	private Combustible combustible;
	
	private Cambio cambio;
	
	private Venta venta;
	
	private Alquiler alquiler;
	
	private Envio envio;
	
	private Mecanico mecanico;
	
	private Recogida recogida;
	
	List<Vehiculos> vehiculos = new ArrayList<Vehiculos>();
	List<Disponible> disponibles = new ArrayList<Disponible>();
	List<Cambio> cambios = new ArrayList<Cambio>();
	List<Concesionario> concesionarios = new ArrayList<Concesionario>();
	List<Combustible> combustibles = new ArrayList<Combustible>();
	List<SeguroVehiculo> segurosVehiculos = new ArrayList<SeguroVehiculo>();
	
	@BeforeEach
	void setup() {
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
		reserva.setFechaGastos(LocalDate.of(2016, 9, 3));
		reserva.setFianza(317.8);
		reserva.setCliente(cliente);
		
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
		
		venta = new Venta();
		venta.setId(1);
		venta.setFecha(LocalDate.of(2020, 12, 22));
		venta.setCliente(cliente);
		venta.setReserva(reserva);
		venta.setVehiculo(vehiculo);
		
		recogida = new Recogida();
		recogida.setId(1);
		recogida.setCodigoPostal("41005");
		recogida.setDireccion("C/Aznalcazar");
		recogida.setLocalidad("Sevilla");
		recogida.setPais("España");
		recogida.setProvincia("Sevilla");
		recogida.setHora(LocalTime.of(12, 0, 0));
		recogida.setMecanico(mecanico);
		
		mecanico = new Mecanico();
		mecanico.setDni("47565973E");
		mecanico.setLastName("Molinas Trujillo");
		mecanico.setEmail("alvmoltrujillo@gmail.com");
		mecanico.setFirstName("Álvaro");
		mecanico.setTelefono("625496828");
		mecanico.setSueldo(1730.0);		
		
		envio = new Envio();
		envio.setId(1);
		envio.setCodigoPostal("41005");
		envio.setDireccion("C/Aznalcazar");
		envio.setLocalidad("Sevilla");
		envio.setPais("España");
		envio.setProvincia("Sevilla");
		envio.setHora(LocalTime.of(10, 0, 0));
		envio.setMecanico(mecanico);
		
		alquiler = new Alquiler();
		alquiler.setId(1);
		alquiler.setFechaInicio(LocalDate.of(2020, 12, 21));
		alquiler.setFechaFin(LocalDate.of(2021, 3, 2));
		alquiler.setLimiteKM(20000);
		alquiler.setDepLleno(false);
		alquiler.setCliente(cliente);
		alquiler.setEnvio(envio);
		alquiler.setRecogida(recogida);
		alquiler.setReserva(reserva);
		alquiler.setVehiculo(vehiculo);
		
		vehiculos.add(vehiculo);
		given(vehiculosService.findVehiculoById(anyInt())).willReturn(vehiculo);
		given(vehiculosService.findDisponibleById(anyInt())).willReturn(disponible);
		given(vehiculosService.findAllVehiculos()).willReturn(vehiculos);
		given(vehiculosService.findByDisponible(anyInt())).willReturn(vehiculos);
		given(vehiculosService.findAllDisponibles()).willReturn(disponibles);
		given(vehiculosService.findAllCambios()).willReturn(cambios);
		given(concesionarioService.findAllConcesionarios()).willReturn(concesionarios);
		given(vehiculosService.findAllCombustibles()).willReturn(combustibles);
		given(vehiculosService.findAllSeguros()).willReturn(segurosVehiculos);
	}
	
	@WithMockUser(value= "spring")
	@Test
	void testInitCreationForm() throws Exception{
		
		mockMvc.perform(get("/vehiculos/new"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("vehiculos"))
		.andExpect(model().attributeExists("disponibles"))
		.andExpect(model().attributeExists("cambios"))
		.andExpect(model().attributeExists("concesionarios"))
		.andExpect(model().attributeExists("combustibles"))
		.andExpect(model().attributeExists("seguros"))
		.andExpect(view().name("vehiculos/createOrUpdateVehiculoForm"));
		
	}
	
	@WithMockUser(value= "spring")   //REVISAR
	@Test
	void testProcessCreationFormSuccess() throws Exception{
		vehiculo.setId(1);
		vehiculo.setCaracteristicas("");
		vehiculo.setEstado("");
		vehiculo.setKmActuales(null);
		vehiculo.setMaletero(null);
		vehiculo.setMarca("");
		vehiculo.setMatricula("");
		vehiculo.setModelo("");
		vehiculo.setPlazas(null);
		vehiculo.setSeguroVehiculo(seguroVehiculo);
		mockMvc.perform(post("/vehiculos/new")
				.param("id", "1")
				.param("caracteristicas", "Espacioso y rápido")
				.param("modelo", "Gallardo")
				.param("matricula", "6874 KJU")
				.param("estado", "Usado")
				.param("marca", "Lamborghini")
				.param("puertas", "2")
				.param("plazas", "4")
				.param("maletero", "100")
				.param("kmActuales", "1000")
				.param("venta", "venta")
				.with(csrf())
				.param("alquileres", "")
				.param("ventas", "")
				.param("alquileres", "")
				.param("incidencias", "")
				.param("concesionario", "")
				.param("disponible", "")
				.param("cambio", "")
				.param("combustible", "")
				.param("seguroVehiculo", "")
				.param("oferta", ""))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/vehiculos/null"));
	}
	
	@WithMockUser(value= "spring")
	@Test
	void testProcessCreationFormErrors() throws Exception{
		mockMvc.perform(post("/vehiculos/new").param("caracteristicas", "espacioso").param("modelo", "X6")
				.with(csrf())
				.param("matricula", ""))
		.andExpect(model().attributeHasErrors("vehiculos"))
		.andExpect(model().attributeHasFieldErrors("vehiculos", "matricula"))
		.andExpect(status().isOk())
		.andExpect(view().name("vehiculos/createOrUpdateVehiculoForm"));
	}
	
	@WithMockUser(value= "spring")
	@Test
	void testInitUpdateForm() throws Exception{
		mockMvc.perform(get("/vehiculos/{vehiculoId}/edit", 1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("vehiculos"))
		.andExpect(model().attribute("vehiculos", vehiculo))
		.andExpect(model().attributeExists("disponibles"))
		.andExpect(model().attribute("disponibles", disponibles))
		.andExpect(model().attributeExists("cambios"))
		.andExpect(model().attribute("cambios", cambios))
		.andExpect(model().attributeExists("concesionarios"))
		.andExpect(model().attribute("concesionarios", concesionarios))
		.andExpect(model().attributeExists("combustibles"))
		.andExpect(model().attribute("combustibles", combustibles))
		.andExpect(model().attributeExists("seguros"))
		.andExpect(model().attribute("seguros", segurosVehiculos))
		.andExpect(view().name("vehiculos/createOrUpdateVehiculoForm"));
	}
	
	@WithMockUser(value= "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception{
		
		mockMvc.perform(post("/vehiculos/{vehiculoId}/edit", 1).param("caracteristicas", "Espacioso y rápido")
				.param("modelo", "Gallardo")
				.param("matricula", "6874 KJU")
				.param("estado", "Usado")
				.param("marca", "Lamborghini")
				.param("puertas", "2")
				.param("plazas", "4")
				.param("maletero", "100")
				.param("kmActuales", "1000")
				.with(csrf())
				.param("alquileres", "")
				.param("ventas", "")
				.param("alquileres", "")
				.param("incidencias", "")
				.param("concesionario", "")
				.param("disponible", "")
				.param("cambio", "")
				.param("combustible", "")
				.param("seguroVehiculo", "")
				.param("oferta", ""))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/vehiculos/{vehiculoId}"));
	}
	
	@WithMockUser(value= "spring")
	@Test
	void testProcessUpdateFormErrors() throws Exception{
		mockMvc.perform(post("/vehiculos/{vehiculoId}/edit", 1).param("caracteristicas", "espacioso y rápido").param("modelo", "Gallardo")
				.with(csrf())
				.param("matricula", "")
				.param("estado", ""))
		.andExpect(model().attributeHasErrors("vehiculos"))
		.andExpect(model().attributeHasFieldErrors("vehiculos", "matricula"))
		.andExpect(model().attributeHasFieldErrors("vehiculos", "estado"))
		.andExpect(status().isOk())
		.andExpect(view().name("vehiculos/createOrUpdateVehiculoForm"));
	}
	
	@WithMockUser(value= "spring")
	@Test
	void testShowVehiculo() throws Exception {
		
		mockMvc.perform(get("/vehiculos/{vehiculoId}", 1))
		.andExpect(status().isOk())
		.andExpect(model().attribute("vehiculos", hasProperty("marca", is("Lamborghini"))))
		.andExpect(model().attribute("vehiculos", hasProperty("matricula", is("6874 KJU"))))
		.andExpect(model().attribute("vehiculos", hasProperty("modelo", is("Gallardo"))))
		.andExpect(model().attribute("vehiculos", hasProperty("puertas", is(2))))
		.andExpect(model().attribute("vehiculos", hasProperty("plazas", is(4))))
		.andExpect(model().attribute("vehiculos", hasProperty("maletero", is(100))))		
		.andExpect(model().attribute("vehiculos", hasProperty("caracteristicas", is("Espacioso / Amplio"))))
		.andExpect(model().attribute("vehiculos", hasProperty("kmActuales", is(1000))))
		.andExpect(view().name("vehiculos/vehiculoDetails"));
	}
	
	@WithMockUser(value= "spring")
	@Test
	void testShowVehiculosList() throws Exception{
		
		mockMvc.perform(get("/vehiculos"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("vehiculos"))
		.andExpect(model().attribute("vehiculos", vehiculos))
		.andExpect(model().attributeExists("disponible"))
		.andExpect(model().attribute("disponible", disponibles))
		.andExpect(view().name("vehiculos/mostrarVehiculos"));
		
	}
	
	@WithMockUser(value= "spring") 
	@Test
	void testDeleteVehiculo() throws Exception{
		
		mockMvc.perform(get("/vehiculos/{vehiculoId}/delete", 1))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/vehiculos"));
		
	}
	
	@WithMockUser(value= "spring")
	@Test
	void testShowVehiculosDisponibleList() throws Exception{

		mockMvc.perform(get("/vehiculos/disponible/{disponibleId}", 1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("vehiculos"))
		.andExpect(model().attribute("vehiculos", vehiculos))
		.andExpect(model().attributeExists("disponible"))
		.andExpect(model().attribute("disponible", disponibles))
		.andExpect(view().name("vehiculos/mostrarVehiculos"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitFindForm() throws Exception{
		
		mockMvc.perform(get("/searchVehiculos"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("vehiculo"))
		.andExpect(view().name("vehiculos/mostrarVehiculos"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testSearchVehiculos() throws Exception{
		
		mockMvc.perform(post("/doSearchVehiculos")
				.with(csrf())
				.param("marca", "Opel"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("vehiculos/mostrarVehiculos"));
	}
	
	@WithMockUser(value= "spring")
	@Test
	void testReservarVehiculos() throws Exception{
		mockMvc.perform(get("/reservar/{vehiculoId}", 1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("vehiculos"))
		.andExpect(model().attribute("vehiculos", vehiculo))
		.andExpect(view().name("reservas/createReservaForm"));
	}
}