package com.springframework.samples.madaja.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.VehiculosService;

@RestController
@RequestMapping(value = "/api/v1/alquileres/")
public class AlquilerControllerAPI {
	
	private final AlquilerService alquilerService;
	
	private final ClienteService clienteService;
	
	private final VehiculosService vehiculosService;
	
	@Autowired
	public AlquilerControllerAPI(AlquilerService alquilerService,ClienteService clienteService, 
			VehiculosService vehiculosService) {
		this.alquilerService = alquilerService;
		this.clienteService = clienteService;
		this.vehiculosService = vehiculosService;
	}
	
	@GetMapping(value= "/{clienteId}")
	public List<Alquiler> getAlquileresCliente(@PathVariable int clienteId) {
		Cliente cliente = this.clienteService.findClienteById(clienteId);
		return alquilerService.findAlquilerByDni(cliente.getDni()).stream().collect(Collectors.toList());
		
	}
}
