package com.springframeworksamples.madaja.web;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springframework.samples.madaja.configuration.SecurityConfiguration;
import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.ReservaService;
import com.springframework.samples.madaja.service.VentaService;
import com.springframework.samples.madaja.web.ClienteController;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(value = ClienteController.class)
class ClienteControllerTests {
	@Autowired
	private ClienteController clienteController;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private VentaService ventaService;
	
	@MockBean
	private ReservaService reservaService;
	
	@MockBean
	private AlquilerService alquilerService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Cliente cliente;
	
	@BeforeEach
	void setup() {
		cliente = new Cliente();
		cliente.setDni("49958021C");
		cliente.setNombre("Alejandro");
		cliente.setApellidos("Piury");
		cliente.setTelefono("640126156");
		cliente.setEmail("alejandropiuryp@gmail.com");
		cliente.setEsConflictivo("No lo es");
		given(this.clienteService.findClienteByDni("49958021C")).willReturn(cliente);
		given(this.ventaService.findVentasByDni("49958021C")).willReturn(null);
		List<Cliente> lista = new ArrayList<Cliente>();
		lista.add(cliente);
	//	given(this.clienteService.findAllClientes()).willReturn(lista);
		int r = 0;
	}
		@WithMockUser(value = "spring")
		@Test
	void testConsultClientes() throws Exception{
		mockMvc.perform(get("/clientes/ventas/{clienteId}", "49958021C"));
	}
}
