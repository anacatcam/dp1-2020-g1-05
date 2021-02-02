package com.springframework.samples.madaja.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


import java.time.LocalDate;
import java.time.LocalTime;

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
import com.springframework.samples.madaja.model.Cambio;
import com.springframework.samples.madaja.model.Combustible;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.IncidenciaService;
import com.springframework.samples.madaja.service.MecanicoService;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.web.IncidenciaController;

@WebMvcTest(controllers=IncidenciaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class IncidenciaControllerTests {
	
	@Autowired
	private IncidenciaController incidenciaController;
	
	@MockBean
	private IncidenciaService incidenciaService;
	
	@MockBean
	private VehiculosService vehiculosService;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private MecanicoService mecanicoService;

	@Autowired
	private MockMvc mockMvc;
	
	private Incidencia incidencia;
	
	private SeguroVehiculo seguroVehiculo;
	
	private Oferta oferta;
	
	private Disponible disponible;
	
	private Concesionario concesionario;
	
	private Combustible combustible;
	
	private Cambio cambio;
	
	private Vehiculos vehiculo;
	
	@BeforeEach
	void setup() {
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
		
		given(vehiculosService.findVehiculoById(anyInt())).willReturn(vehiculo);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception{
		mockMvc.perform(get("/vehiculos/{vehiculoId}/incidencia/new",1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("incidencia"))
		.andExpect(view().name("incidencia/createIncidenciaForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSucess() throws Exception{
		mockMvc.perform((post("/vehiculos/{vehiculoId}/incidencia/new",1)
				.param("descripcion", "Golpe trasero")
				.param("mecanicos", "")
				.param("solucionada", "true")
				.with(csrf())))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/vehiculos/{vehiculoId}"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormErrors() throws Exception{
		mockMvc.perform(post("/vehiculos/{vehiculoId}/incidencia/new", 1)
				.param("descripcion", "")
				.param("mecanicos", "")
				.param("solucionada", "")
				.with(csrf()))
		.andExpect(model().attributeHasErrors("incidencia"))
		.andExpect(model().attributeHasFieldErrors("incidencia", "descripcion"))
		.andExpect(model().attributeHasFieldErrors("incidencia", "solucionada"))
		.andExpect(status().isOk())
		.andExpect(view().name("incidencia/updateIncidenciaForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception{
		given(incidenciaService.findIncidenciaById(anyInt())).willReturn(incidencia);
		
		mockMvc.perform(get("/vehiculos/{vehiculoId}/incidencia/{incidenciaId}/edit", 1,1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("incidencia"))
		.andExpect(model().attribute("incidencia", incidencia))
		.andExpect(view().name("incidencia/updateIncidenciaForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSucess() throws Exception{
		given(incidenciaService.findIncidenciaById(anyInt())).willReturn(incidencia);
		
		mockMvc.perform(post("/vehiculos/{vehiculoId}/incidencia/{incidenciaId}/edit",1,1)
				.param("descripcion", "Golpe frontal")
				.param("mecanicos", "")
				.param("solucionada", "true")
				.with(csrf()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/vehiculos/{vehiculoId}"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormError() throws Exception{
		mockMvc.perform(post("/vehiculos/{vehiculoId}/incidencia/{incidenciaId}/edit",1,1)
				.param("descripcion", "")
				.param("mecanicos", "")
				.param("solucionada", "")
				.with(csrf()))
		.andExpect(model().attributeHasErrors("incidencia"))
		.andExpect(model().attributeHasFieldErrors("incidencia", "descripcion"))
		.andExpect(model().attributeHasFieldErrors("incidencia", "solucionada"))
		.andExpect(status().isOk())
		.andExpect(view().name("incidencia/updateIncidenciaForm"));
	}
}
