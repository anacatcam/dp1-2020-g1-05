package com.springframework.samples.madaja.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyInt;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.repository.OfertaRepository;

@ExtendWith(MockitoExtension.class)
public class OfertaServiceTests {

	@Mock
	private OfertaRepository ofertaRepository;
	
	
	protected OfertaService ofertaService;
	
	@BeforeEach
	void setUp() {
		ofertaService = new OfertaService(ofertaRepository);
	}
	
	@Test
	public void testFindAllOfertas() throws Exception{
		Oferta oferta = new Oferta();
		oferta.setId(1);
		oferta.setName("Oferta 1");
		oferta.setDescuento(22.0);
		oferta.setFechaLimite(LocalDate.of(2020, 6, 12));
		oferta.setHoraLimite(LocalTime.of(2, 3, 4));
		List<Oferta> ofertas = new ArrayList<Oferta>();
		ofertas.add(oferta);
		when(ofertaRepository.findAll()).thenReturn(ofertas);
		
		ofertaService.findAllOfertas();
		
		verify(ofertaRepository).findAll();
		assertEquals(ofertas, ofertaService.findAllOfertas());
	}
	
	@Test
	public void testFindOfertaById() throws Exception{
		Oferta oferta = new Oferta();
		oferta.setId(1);
		oferta.setName("Oferta 1");
		oferta.setDescuento(22.0);
		oferta.setFechaLimite(LocalDate.of(2020, 6, 12));
		oferta.setHoraLimite(LocalTime.of(2, 3, 4));
		
		when(ofertaRepository.findById(anyInt())).thenReturn(oferta);
		
		ofertaService.findOfertaById(anyInt());
		
		verify(ofertaRepository).findById(anyInt());
		assertEquals(oferta, ofertaService.findOfertaById(anyInt()));
	}
	
	@Test
	public void testSaveOferta() throws Exception {
		Oferta oferta = new Oferta();
		oferta.setId(1);
		oferta.setName("Oferta 1");
		oferta.setDescuento(22.0);
		oferta.setFechaLimite(LocalDate.of(2020, 6, 12));
		oferta.setHoraLimite(LocalTime.of(2, 3, 4));
		
		ofertaService.saveOferta(oferta);
		verify(ofertaRepository).save(oferta);
	}
	
	@Test
	public void testDeleteById() throws Exception {
		ofertaService.deleteById(anyInt());
		verify(ofertaRepository).deleteById(anyInt());
	}
}
