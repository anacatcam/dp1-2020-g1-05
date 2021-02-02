package com.springframework.samples.madaja.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.EnvioService;
import com.springframework.samples.madaja.service.VentaService;

@RestController
@RequestMapping(value = "/api/v1/concesionarios/concesionario/{concesionarioId}")
public class EnvioControllerAPI {
	
	private final EnvioService envioService;
	private final AlquilerService alquilerService;
	private final VentaService ventaService;

	@Autowired
	public EnvioControllerAPI(EnvioService envioService, AlquilerService alquilerService, VentaService ventaService) {
		this.envioService = envioService;
		this.alquilerService = alquilerService;
		this.ventaService = ventaService;
	}
	
	@GetMapping(value = "/enviosAlquileres")
	public void showEnviosAlquiler() {
		
		
		
	}
	
	@GetMapping(value="/enviosVentas")
	public void showEnviosVenta() {
		
	}
	
}
