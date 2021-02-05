package com.springframework.samples.madaja.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.util.APIerror;

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
