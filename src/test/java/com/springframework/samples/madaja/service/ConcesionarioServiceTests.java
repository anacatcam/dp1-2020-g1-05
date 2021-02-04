package com.springframework.samples.madaja.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.search.Search;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.mysql.cj.Session;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.repository.ConcesionarioRepository;


@ExtendWith(MockitoExtension.class)
public class ConcesionarioServiceTests {
	
	@Autowired
	protected ConcesionarioService concesionarioService;
	
	@Mock
	private ConcesionarioRepository concesionarioRepository;
	
	private Concesionario concesionario;
	
	@BeforeEach
	void setUp() {
		concesionarioService = new ConcesionarioService(concesionarioRepository);
		
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
	
	@Test
    void testFindAllConcesionarios() throws Exception{
		List<Concesionario> concesionarios = new ArrayList<Concesionario>();
		concesionarios.add(concesionario);
		when(concesionarioRepository.findAll()).thenReturn(concesionarios);
		
		concesionarioService.findAllConcesionarios();
		
		verify(concesionarioRepository).findAll();
		assertEquals(concesionarios, concesionarioService.findAllConcesionarios());
    }
	
	@Test
	void testFindConcesioarioById() throws Exception{
		when(concesionarioRepository.findById(anyInt())).thenReturn(concesionario);
		
		concesionarioService.findConcesionarioById(anyInt());
		
		verify(concesionarioRepository).findById(anyInt());
		assertEquals(concesionario, concesionarioService.findConcesionarioById(anyInt()));
		
	}
	
	@Test
	void shouldSaveConcesionario() {
		concesionarioService.saveConcesionario(concesionario);
		
		verify(concesionarioRepository).save(concesionario);
	}
}
