package com.springframework.samples.madaja.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.repository.OfertaRepository;

@ExtendWith(MockitoExtension.class)
public class OfertaServiceTests {

	@Mock
	private OfertaRepository ofertaRepository;
	
	@Autowired
	protected OfertaService ofertaService;
	
	@BeforeEach
	void setUp() {
		ofertaService = new OfertaService(ofertaRepository);
	}
	
	@Mock
    private Pageable pageableMock;
	
	@Mock
    private Page<Oferta> page;
	
	
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
	
	//PAGINACIÓN
	@Test
	public void testGetAll() throws Exception{
		Oferta oferta = new Oferta();
		oferta.setId(1);
		oferta.setName("Oferta 1");
		oferta.setDescuento(22.0);
		oferta.setFechaLimite(LocalDate.of(2020, 6, 12));
		oferta.setHoraLimite(LocalTime.of(2, 3, 4));
		List<Oferta> ofertas = new ArrayList<Oferta>();
		ofertas.add(oferta);
		
		Pageable pageable = PageRequest.of(0, 8);
		Page<Oferta> page = new PageImpl<Oferta>(ofertas);
				
		when(ofertaRepository.findAll(pageable)).thenReturn(page);
		
		ofertaService.getAll(pageable);
		
		verify(ofertaRepository).findAll(pageable);
		assertEquals(page, ofertaService.getAll(pageable));
	}
	
	
}
