package com.springframework.samples.madaja.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyInt;

import org.assertj.core.util.Arrays;
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
import org.springframework.ui.ModelMap;

import com.springframework.samples.madaja.configuration.SecurityConfiguration;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.OfertaService;
import com.springframework.samples.madaja.service.VehiculosService;

@WebMvcTest(controllers=OfertaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
class OfertaControllerTests {
	
	@Autowired
	private OfertaController ofertaController;
	
	@MockBean
	private VehiculosService vehiculosService;
	
	@MockBean
	private OfertaService ofertaService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Oferta oferta;
	
	@BeforeEach
	void setUp() {
		oferta = new Oferta();
		oferta.setId(1);
		oferta.setName("Oferta 1");
		oferta.setDescuento(22.0);
		oferta.setFechaLimite(LocalDate.of(2020, 6, 12));
		oferta.setHoraLimite(LocalTime.of(2, 3, 4));
		
		List<Vehiculos> matriculas = new ArrayList<Vehiculos>();
		List<Vehiculos> vehiculos = new ArrayList<Vehiculos>();
		
		given(ofertaService.findOfertaById(anyInt())).willReturn(oferta);
		given(vehiculosService.findByOferta(anyInt())).willReturn(vehiculos);
		given(vehiculosService.findAllVehiculosDisponiblesYsinOfertas()).willReturn(matriculas);
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testShowOfertasList() throws Exception{
		List<Oferta> ofertas = new ArrayList<Oferta>();
		ofertas.add(oferta);
		given(ofertaService.findAllOfertas()).willReturn(ofertas);
		mockMvc.perform(get("/oferta")).andExpect(status().isOk()).andExpect(model().attributeExists("ofertas")).andExpect(view().name("oferta/mostrarOfertas")).andExpect(model().attribute("ofertas", ofertas));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowOferta() throws Exception{
		List<Vehiculos> vehiculos = new ArrayList<Vehiculos>();
				
		given(ofertaService.findOfertaById(1)).willReturn(oferta);
		given(vehiculosService.findByOferta(1)).willReturn(vehiculos);
		
		mockMvc.perform(get("/oferta/{ofertaId}", 1)).andExpect(status().isOk()).andExpect(model().attributeExists("oferta")).andExpect(model().attribute("oferta", oferta)).andExpect(view().name("oferta/ofertaDetails"))
		.andExpect(model().attributeExists("vehiculos")).andExpect(model().attribute("vehiculos", Lists.emptyList())).andExpect(view().name("oferta/ofertaDetails"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCreationFormOferta() throws Exception {
		mockMvc.perform(get("/oferta/new")).andExpect(status().isOk()).andExpect(model().attributeExists("oferta"))
		.andExpect(view().name("oferta/createOfertaForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessFormOfertaSuccess() throws Exception {
		mockMvc.perform(post("/oferta/new").param("name", "Oferta 1").param("descuento", "22.0")
				.with(csrf())
				.param("fechaLimite", "2020-06-12")
				.param("horaLimite", "02:03:04")
				.param("matriculas", "none"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/oferta"));;
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessFormOfertaErrors() throws Exception {
		mockMvc.perform(post("/oferta/new").param("name", "Oferta 1").param("descuento", "22.0")
				.with(csrf())
				.param("fechaLimite", "2020/06/12")
				.param("horaLimite", "02-03-04")
				.param("matriculas", "none"))
		.andExpect(model().attributeHasErrors("oferta"))
		.andExpect(model().attributeHasFieldErrors("oferta", "horaLimite"))
		.andExpect(status().isOk())
		.andExpect(view().name("oferta/createOfertaForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testUpdateFormOferta() throws Exception {
		List<Vehiculos> vehiculos = new ArrayList<Vehiculos>();
		mockMvc.perform(get("/oferta/{ofertaId}/edit",1)).andExpect(status().isOk()).andExpect(model().attributeExists("oferta"))
		.andExpect(model().attribute("oferta", oferta)).andExpect(model().attributeExists("vehiculos"))
		.andExpect(model().attribute("vehiculos", vehiculos)).andExpect(view().name("oferta/updateOfertaForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateOfertaSuccess() throws Exception {
		mockMvc.perform(post("/oferta/{ofertaId}/edit",1).param("name", "Oferta 1").param("descuento", "22.0")
				.with(csrf())
				.param("fechaLimite", "2020-06-12")
				.param("horaLimite", "02:03:04")
				.param("matriculas", "none"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/oferta"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdateOfertaErrors() throws Exception {
		mockMvc.perform(post("/oferta/{ofertaId}/edit",1).param("name", "Oferta 1").param("descuento", "22.0")
				.with(csrf())
				.param("fechaLimite", "2020/06/12")
				.param("horaLimite", "02-03-04")
				.param("matriculas", "none"))
		.andExpect(model().attributeHasErrors("oferta"))
		.andExpect(model().attributeHasFieldErrors("oferta", "horaLimite"))
		.andExpect(status().isOk())
		.andExpect(view().name("oferta/updateOfertaForm"));
	}
	
	//BORRAR OFERTA?
}
