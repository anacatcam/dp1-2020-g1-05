package com.springframework.samples.madaja.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.VehiculosService;

@RestController
@RequestMapping(value = "/api/v1/vehiculos/")
public class VehiculosControllerAPI {

	private VehiculosService vehiculosService;
	
	public VehiculosControllerAPI(VehiculosService vehiculosService) {
		this.vehiculosService = vehiculosService;
	}
	
	@GetMapping(value = "/disponibles-ofertas")
	public List<Vehiculos> allDisponiblesOfertas(){
		return vehiculosService.findAllVehiculosDisponiblesYsinOfertas().stream().collect(Collectors.toList());
	}
	
	@GetMapping(value = "/oferta/{id}")
	public List<Vehiculos> allVehiculosByOferta(@PathVariable int id){
		return vehiculosService.findByOferta(id).stream().collect(Collectors.toList());
	}
}
