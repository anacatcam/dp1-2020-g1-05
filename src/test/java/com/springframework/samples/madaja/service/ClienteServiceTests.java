package com.springframework.samples.madaja.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.springframework.samples.madaja.model.Authorities;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.User;
import com.springframework.samples.madaja.repository.AuthoritiesRepository;
import com.springframework.samples.madaja.repository.ClienteRepository;
import com.springframework.samples.madaja.repository.UserRepository;
import com.springframework.samples.madaja.util.EntityUtils;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTests {

	@Mock
	private ClienteRepository clienteRepository;
	

	@Autowired
	protected ClienteService clienteService;
	
	@Mock
	private UserService userService;
	
	@Mock
	private AuthoritiesService authoritiesService;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	AuthoritiesRepository authoritiesReposioty;
	
	private User usuario;
	
	private Authorities auth;
	
	private Cliente cliente;
	
	@BeforeEach
	void setUp() {
		clienteService = new ClienteService(clienteRepository, userService, authoritiesService);
		userService = new UserService(userRepository);
		authoritiesService = new AuthoritiesService(authoritiesReposioty, userService);

		usuario = new User();
		usuario.setUsername("alejandro");
		usuario.setEnabled(Boolean.TRUE);
		usuario.setPassword("contraseña3");
		
		auth = new Authorities();
		auth.setUser(usuario);
		auth.setAuthority("cliente");
		
		cliente = new Cliente();
		cliente.setId(1);
		cliente.setFirstName("Alejandro");
		cliente.setLastName("Castellano Sanz");
		cliente.setDni("12422051G");
		cliente.setEmail("alejcastz@gmail.co");
		cliente.setEsConflictivo("No lo es");
		cliente.setTelefono("637666517");
		cliente.setUser(usuario);
		cliente.setDiasRetraso(0);
	}
	
	@Test
	void testFindClienteByDni() throws Exception{
		when(clienteRepository.findByDni(anyString())).thenReturn(cliente);
		
		clienteService.findClienteByDni(anyString());
		
		verify(clienteRepository).findByDni(anyString());
		assertEquals(cliente, clienteService.findClienteByDni(anyString()));
	}
	
	@Test
	void testFindAll() throws Exception{
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente);
		Iterable<Cliente> clientesIt = clientes;
		when(clienteRepository.findAll()).thenReturn(clientesIt);
		
		clienteService.findAllClientes();
		
		verify(clienteRepository).findAll();
		assertEquals(clientesIt, clienteService.findAllClientes());
		
	}
	
	
	@Test
	void testSaveCliente() throws Exception{
		when(userService.findUser(anyString())).thenReturn(Optional.of(cliente.getUser()));
		
		clienteService.saveCliente(cliente);
		userService.saveUser(cliente.getUser());
		authoritiesService.saveAuthorities(cliente.getUser().getUsername(), "cliente");
		
		verify(clienteRepository).save(cliente);
		verify(userRepository).save(usuario);
		verify(authoritiesReposioty).save(auth);
	}
	
	
	@Test
	void testFindClienteByUsername() throws Exception{
		when(clienteRepository.findByUsername(anyString())).thenReturn(cliente);
		
		clienteService.findClienteByUsername(anyString());
		
		verify(clienteRepository).findByUsername(anyString());
		assertEquals(cliente, clienteService.findClienteByUsername(anyString()));
	}
}
