package com.springframework.samples.madaja.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.springframework.samples.madaja.model.Mecanico;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.repository.MecanicoRepository;
import com.springframework.samples.madaja.repository.OfertaRepository;

@ExtendWith(MockitoExtension.class)
public class MecanicoServiceTests {
	@Mock
	private MecanicoRepository mecanicoRepository;
	
	@Autowired
	protected MecanicoService mecanicoService;
	
	
	private Mecanico mecanico;
	
	@BeforeEach
	void setUp() {
		mecanicoService = new MecanicoService(mecanicoRepository);
		
		mecanico = new Mecanico();
		mecanico.setDni("47565973E");
		mecanico.setApellidos("Molinas Trujillo");
		mecanico.setEmail("alvmoltrujillo@gmail.com");
		mecanico.setNombre("Álvaro");
		mecanico.setTelefono("625496828");
		mecanico.setSueldo(1730.0);		
	}
	
	@Test
	public void testFindById() throws Exception{
		when(mecanicoRepository.findById(anyString())).thenReturn(mecanico);
		
		mecanicoService.findById(anyString());
		
		verify(mecanicoRepository).findById(anyString());
		assertEquals(mecanico, mecanicoService.findById(anyString()));
	}
	
	@Test
	public void testFindAll() throws Exception{
		List<Mecanico> mecanicos = new ArrayList<Mecanico>();
		mecanicos.add(mecanico);
		
		when(mecanicoRepository.findAll()).thenReturn(mecanicos);
		
		mecanicoService.findAll();
		
		verify(mecanicoRepository).findAll();
		assertEquals(mecanicos, mecanicoService.findAll());
	}
	
	@Test
	public void testFindMecanicosByIncidencia() throws Exception{
		List<Mecanico> mecanicos = new ArrayList<Mecanico>();
		mecanicos.add(mecanico);
		
		when(mecanicoRepository.findMecanicosByIncidencia(anyInt())).thenReturn(mecanicos);
		
		mecanicoService.findMecanicosByIncidencia(anyInt());
		
		verify(mecanicoRepository).findMecanicosByIncidencia(anyInt());
		assertEquals(mecanicos, mecanicoService.findMecanicosByIncidencia(anyInt()));
	}
	
}
