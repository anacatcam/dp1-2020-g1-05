package com.springframework.samples.madaja.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.IncidenciaService;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.web.IncidenciaController;

@WebMvcTest(value = IncidenciaController.class,excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class IncidenciaControllerTests {
	
	@Autowired
	private IncidenciaController incidenciaController;
	
	@MockBean
	private IncidenciaService incidenciaService;
	
	@MockBean
	private VehiculosService vehiculosService;

	@Autowired
	private MockMvc mockMvc;
	
	private Incidencia incidencia;
	
	@BeforeEach
	void setup() {
		incidencia = new Incidencia();
		incidencia.setDescripcion("Retrovisor derecho destrozado");
		incidencia.setSolucionada(false);
		incidencia.setId(7);
		Vehiculos vehiculo=new Vehiculos();
		vehiculo.setId(12);
		vehiculo.setCaracteristicas("Fiabilidad, espacioso");
		vehiculo.setEstado("Cómo nuevo");
		vehiculo.setKmActuales(15000);
		vehiculo.setMaletero(300);
		vehiculo.setMarca("BMW");
		vehiculo.setMatricula("7523 DPX");
		vehiculo.setModelo("MX6");
		incidencia.setVehiculos(vehiculo);
		given(this.incidenciaService.findIncidenciaById(7)).willReturn(incidencia);
		given(this.vehiculosService.findVehiculoById(12)).willReturn(vehiculo);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testConsultaIncidencias() throws Exception{
		mockMvc.perform(get("/vehiculos/{vehiculoId}/incidencia/{incidenciaId}","12", "7"));
	}
	

}
