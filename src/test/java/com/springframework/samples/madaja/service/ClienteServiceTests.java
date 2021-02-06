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
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.User;
import com.springframework.samples.madaja.repository.ClienteRepository;
import com.springframework.samples.madaja.util.EntityUtils;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTests {

	@Mock
	private ClienteRepository clienteRepository;
	
	@Mock 
	private EntityManager entityManager;
	
	@Autowired
	protected ClienteService clienteService;
	
	private User usuario;
	
	private Cliente cliente;
	
	@BeforeEach
	void setUp() {
		clienteService = new ClienteService(clienteRepository,entityManager);
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
	
	/*
	@Test
	void testSaveCliente() throws Exception{
		clienteService.saveCliente(cliente);
		
		
		verify(clienteRepository).save(cliente);
	}
	*/
	
	@Test
	void testFindClienteByUsername() throws Exception{
		when(clienteRepository.findByUsername(anyString())).thenReturn(cliente);
		
		clienteService.findClienteByUsername(anyString());
		
		verify(clienteRepository).findByUsername(anyString());
		assertEquals(cliente, clienteService.findClienteByUsername(anyString()));
	}
}
