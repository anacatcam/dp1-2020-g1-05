package com.springframework.samples.madaja.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.repository.SeguroVehiculoRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SeguroVehiculoServiceTests {
	
	@Mock
	private SeguroVehiculoRepository seguroVehiculoRepository;
	
	@Autowired
	protected SeguroVehiculoService seguroVehiculoService;
	
	private SeguroVehiculo seguroVehiculo;
	private 
	
	@BeforeEach
	void setUp() {
		seguroVehiculoService = new SeguroVehiculoService(seguroVehiculoRepository);
		
		seguroVehiculo = new SeguroVehiculo();
		seguroVehiculo.setId(1);
		seguroVehiculo.setNumeroPoliza("32151");
		seguroVehiculo.setPrecio(450.32);
		seguroVehiculo.setFranquicia(200);
		seguroVehiculo.setCobertura("A todo riesgo");
		seguroVehiculo.setFechaInicio(LocalDate.of(2020, 9, 07));
		seguroVehiculo.setFechaFin(LocalDate.of(2021, 9, 07));
			
	}
	
	@Test
	void testDeleteAllSeguros() throws Exception{
		seguroVehiculoService.deleteAllSeguros();
		verify(seguroVehiculoRepository).deleteAll();	
	}
	
	@Test
	void testFindSeguroVehiculosById() throws Exception{
		when(seguroVehiculoRepository.findById(anyInt())).thenReturn(seguroVehiculo);
		
		seguroVehiculoService.findSeguroVehiculoById(anyInt());
		
		verify(seguroVehiculoRepository).findById(anyInt());
		assertEquals(seguroVehiculo, seguroVehiculoService.findSeguroVehiculoById(anyInt()));		
	}

}
