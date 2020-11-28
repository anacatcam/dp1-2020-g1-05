package com.springframework.samples.madaja.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.util.EntityUtils;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ClienteServiceTests {

	@Autowired
	protected ClienteService clienteService;
	
	@Test
	void shouldFindClienteByDni() {
		Cliente cliente = this.clienteService.findClienteByDni("15442604");
		assertThat(cliente.getNombre()).isEqualTo("Manuel");
	}
	
	@Test
	void shouldFindAll() {
		Iterable<Cliente> clientes = this.clienteService.findAllClientes();
		Iterator<Cliente> clientes_it = clientes.iterator();
		Boolean encontrado = false;
		while(clientes_it.hasNext()) {
			Cliente cliente = clientes_it.next();
			if(cliente.getDni().equals("15442604")) {
				encontrado = true;
				break;
			}
		}
		assertThat(encontrado).isTrue();
		
	}
}
