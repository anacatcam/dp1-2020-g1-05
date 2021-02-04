package com.springframework.samples.madaja.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.springframework.samples.madaja.configuration.SecurityConfiguration;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.service.ConcesionarioService;
import com.springframework.samples.madaja.service.SearchService;

@WebMvcTest(controllers=ConcesionarioController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ConcesionarioControllerTests {
	@Autowired
	private ConcesionarioController concesionarioController;
	
	@MockBean
	private ConcesionarioService concesionarioService;
	
	@MockBean
	private SearchService searchService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Concesionario concesionario;
	
	@BeforeEach
	void setUp() {
		concesionario = new Concesionario();
		concesionario.setId(1);
		concesionario.setCodigoPostal("41063");
		concesionario.setDireccion("Calle Los Pacos");
		concesionario.setEmail("concesionario1@gmail.com");
		concesionario.setLocalidad("Lora del Río");
		concesionario.setProvincia("Sevilla");
		concesionario.setPais("España");
		concesionario.setTelefono("608555102");
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowConcesionariosList() throws Exception{
		List<Concesionario> concesionarios = new ArrayList<Concesionario>();
		concesionarios.add(concesionario);
		given(concesionarioService.findAllConcesionarios()).willReturn(concesionarios);
		
		mockMvc.perform(get("/concesionario"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("concesionarios"))
		.andExpect(model().attribute("concesionarios", concesionarios))
		.andExpect(view().name("concesionario/mostrarConcesionarios"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowConcesionario() throws Exception{
		given(concesionarioService.findConcesionarioById(anyInt())).willReturn(concesionario);
		mockMvc.perform(get("/concesionario/{concesionarioId}",1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("concesionario"))
		.andExpect(model().attribute("concesionario", concesionario))
		.andExpect(view().name("concesionario/concesionarioDetails"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitFindForm() throws Exception{
		
		mockMvc.perform(get("/searchConcesionarios"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("concesionario"))
		.andExpect(view().name("concesionario/mostrarConcesionarios"));
	}
		
	@WithMockUser(value = "spring")
	@Test
	void testSearchConcesionarios() throws Exception{
		
		mockMvc.perform(post("/doSearchConcesionarios")
				.with(csrf())
				.param("provincia", "Sevilla"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("concesionario/mostrarConcesionarios"));
			
	}
}
