package com.springframework.samples.madaja.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
	
	//PAGINACIÓN
	@Test
	public void testGetAll() throws Exception{
		List<Concesionario> concesionarios = new ArrayList<Concesionario>();
		concesionarios.add(concesionario);
				
		Pageable pageable = PageRequest.of(0, 8);
		Page<Concesionario> page = new PageImpl<Concesionario>(concesionarios);
				
		when(concesionarioRepository.findAll(pageable)).thenReturn(page);
		
		concesionarioService.getAll(pageable);
		
		verify(concesionarioRepository).findAll(pageable);
		assertEquals(page, concesionarioService.getAll(pageable));
	}

}
