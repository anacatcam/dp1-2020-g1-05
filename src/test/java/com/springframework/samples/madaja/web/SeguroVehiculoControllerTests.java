package com.springframework.samples.madaja.web;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.service.SeguroVehiculoService;

@WebMvcTest(controllers=SeguroVehiculoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class SeguroVehiculoControllerTests {
	
	@Autowired
	private SeguroVehiculoController seguroVehiculoController;
	
	@MockBean
	private SeguroVehiculoService seguroVehiculoService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private SeguroVehiculo seguroVehiculo;
	
	@BeforeEach
	void setUp() {
		seguroVehiculo= new SeguroVehiculo();
		seguroVehiculo.setId(1);
		seguroVehiculo.setNumeroPoliza("32151");
		seguroVehiculo.setPrecio(450.32);
		seguroVehiculo.setFranquicia(200);
		seguroVehiculo.setCobertura("A todo riesgo");
		seguroVehiculo.setFechaInicio(LocalDate.of(2020, 9, 07));
		seguroVehiculo.setFechaFin(LocalDate.of(2021, 9, 07));
		
	}
	
	@WithMockUser(value= "spring")
	@Test
	void testShowSeguroVehiculo() throws Exception {
		
		given(seguroVehiculoService.findSeguroVehiculoById(1)).willReturn(seguroVehiculo);
		
		mockMvc.perform(get("/vehiculos/{vehiculoId}/seguro/{seguroVehiculoId}/view", 1,1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("seguroVehiculo"))
		.andExpect(model().attribute("seguroVehiculo", seguroVehiculo))
		.andExpect(view().name("seguro/seguroDetails"));
	}
	
}