package com.springframework.samples.madaja.web;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.SeguroCliente;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.SeguroClienteService;
import com.springframework.samples.madaja.service.VehiculosService;

@WebMvcTest(controllers=SeguroClienteController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class SeguroClienteControllerTests {
	
	@Autowired
	private SeguroClienteController seguroClienteController;
	
	@MockBean
	private SeguroClienteService seguroClienteService;
	
	@MockBean
	private VehiculosService vehiculosService;

	@Autowired
	private MockMvc mockMvc;
	
	private SeguroCliente seguroCliente;
	
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
		concesionario.setLocalidad("Lora del R??o");
		concesionario.setProvincia("Sevilla");
		concesionario.setPais("Espa??a");
		concesionario.setTelefono("608555102");
		
		combustible = new Combustible();
		combustible.setId(1);
		combustible.setName("diesel");
		
	    cambio = new Cambio();
		cambio.setId(1);
		cambio.setName("autom??tico");
		
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
		seguroCliente.setId(1);
		seguroCliente.setCobertura("Prueba controller");
		seguroCliente.setFranquicia(150);
		seguroCliente.setFechaInicio(LocalDate.of(2020, 12, 10));
		seguroCliente.setFechaFin(LocalDate.of(2020, 12, 15));
		seguroCliente.setPrecio(390.00);
		seguroCliente.setVehiculos(vehiculo);
		
		given(vehiculosService.findVehiculoById(anyInt())).willReturn(vehiculo);
		given(seguroClienteService.findSeguroClienteById(anyInt())).willReturn(seguroCliente);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception{
		mockMvc.perform(get("/vehiculos/{vehiculoId}/seguroCliente/new",1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("seguroCliente"))
		.andExpect(view().name("seguroCl/createSeguroClForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSucess() throws Exception{
		mockMvc.perform((post("/vehiculos/{vehiculoId}/seguroCliente/new",1)
				.param("cobertura", "Prueba controller test")
				.param("franquicia", "150")
				.param("fechaInicio", "2020-12-10")
				.param("fechaFin", "2020-12-15")
				.param("precio", "390")
				.with(csrf())))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/vehiculos/{vehiculoId}"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormErrors() throws Exception{
		mockMvc.perform(post("/vehiculos/{vehiculoId}/seguroCliente/new", 1)
				.param("cobertura", "")
				.param("franquicia", "")
				.param("fechaInicio", "")
				.param("fechaFin", "")
				.param("precio", "")
				.with(csrf()))
		.andExpect(model().attributeHasErrors("seguroCliente"))
		.andExpect(model().attributeHasFieldErrors("seguroCliente", "cobertura"))
		.andExpect(model().attributeHasFieldErrors("seguroCliente", "franquicia"))
		.andExpect(model().attributeHasFieldErrors("seguroCliente", "fechaInicio"))
		.andExpect(model().attributeHasFieldErrors("seguroCliente", "fechaFin"))
		.andExpect(model().attributeHasFieldErrors("seguroCliente", "precio"))
		.andExpect(status().isOk())
		.andExpect(view().name("seguroCl/createSeguroClForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception{
		
		mockMvc.perform(get("/vehiculos/{vehiculoId}/seguroCliente/{seguroClienteId}/edit", 1,1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("seguroCliente"))
		.andExpect(model().attribute("seguroCliente", seguroCliente))
		.andExpect(view().name("seguroCl/updateSeguroClForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSucess() throws Exception{
		
		mockMvc.perform(post("/vehiculos/{vehiculoId}/seguroCliente/{seguroClienteId}/edit",1,1)
				.param("cobertura", "Prueba controller update")
				.param("franquicia", "170")
				.param("fechaInicio", "2020-12-10")
				.param("fechaFin", "2020-12-18")
				.param("precio", "400")
				.with(csrf()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/vehiculos/{vehiculoId}"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormError() throws Exception{
		mockMvc.perform(post("/vehiculos/{vehiculoId}/seguroCliente/{seguroClienteId}/edit",1,1)
				.param("cobertura", "")
				.param("franquicia", "")
				.param("fechaInicio", "")
				.param("fechaFin", "")
				.param("precio", "")
				.with(csrf()))
		.andExpect(model().attributeHasFieldErrors("seguroCliente", "cobertura"))
		.andExpect(model().attributeHasFieldErrors("seguroCliente", "franquicia"))
		.andExpect(model().attributeHasFieldErrors("seguroCliente", "fechaInicio"))
		.andExpect(model().attributeHasFieldErrors("seguroCliente", "fechaFin"))
		.andExpect(model().attributeHasFieldErrors("seguroCliente", "precio"))
		.andExpect(status().isOk())
		.andExpect(view().name("seguroCl/updateSeguroClForm"));
	}
}
