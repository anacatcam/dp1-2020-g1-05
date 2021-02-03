package com.springframework.samples.madaja.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.springframework.samples.madaja.configuration.SecurityConfiguration;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.User;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.UserService;


@WebMvcTest(controllers=UserController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
class UserControllerTests {
	
	@Autowired
	private UserController userController;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private User usuario;
	private Cliente cliente;
	
	@BeforeEach
	void setUp() {
		usuario = new User();
		usuario.setUsername("alejandro");
		usuario.setEnabled(Boolean.TRUE);
		usuario.setPassword("contraseña3");
		
		cliente= new Cliente();
		cliente.setId(1);
		cliente.setFirstName("Alejandro");
		cliente.setLastName("Castellano Sanz");
		cliente.setDni("12422051G");
		cliente.setEmail("alejcastz@gmail.com");
		cliente.setEsConflictivo("No");
		cliente.setTelefono("637666517");
		cliente.setUser(usuario);
		
		
		
		given(userService.findUser(anyString())).willReturn(Optional.of(usuario));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/users/new"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("cliente"))
		.andExpect(view().name("users/createClienteForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception{
		mockMvc.perform(post("/users/new")
				.with(csrf())
				.param("username", "ale")
				.param("password", "fgas56")
				.param("DNI", "12422051G")
				.param("firstName", "Alejandro")
				.param("lastName", "Castellano Sanz")
				.param("email", "alejcastz@gmail.co")
				.param("esConflictivo", "No")
				.param("telefono", "637666517"))
		.andExpect(status().isOk())
		.andExpect(view().name("users/createClienteForm"));
		
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormErrors() throws Exception{
		mockMvc.perform(post("/users/new")
				.with(csrf())
				.param("username", "ale")
				.param("password", "fgas56")
				.param("DNI", "12422051G")
				.param("firstName", "")
				.param("lastName", "")
				.param("email", "")
				.param("esConflictivo", "No")
				.param("telefono", "-"))
		.andExpect(model().attributeHasErrors("cliente"))
		.andExpect(model().attributeHasFieldErrors("cliente", "email"))
		.andExpect(model().attributeHasFieldErrors("cliente", "telefono"))
		.andExpect(model().attributeHasFieldErrors("cliente", "firstName"))
		.andExpect(model().attributeHasFieldErrors("cliente", "lastName"))
		.andExpect(status().isOk())
		.andExpect(view().name("users/createClienteForm"));
		
	}
	

}
