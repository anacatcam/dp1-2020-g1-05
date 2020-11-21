package com.springframework.samples.madaja.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;

@Controller
public class AlquilerController {

	private final AlquilerService alquilerService;
	

	private final ClienteService clienteService;
	
	@Autowired
	public AlquilerController(AlquilerService alquilerService,ClienteService clienteService) {
		this.alquilerService = alquilerService;
		this.clienteService = clienteService;
	}
	
	@GetMapping(value = {"/MisAlquileres/{clienteId}"})
	public String showMisAlquileres(@PathVariable("clienteId") int id,Map<String, Object> model){
		List<Alquiler> alqList = new ArrayList<>();
		Cliente cliente = this.clienteService.findClienteById(id);
		alqList.addAll(this.alquilerService.findAlquileresCliente(id));
		model.put("alquileres", alqList);
		
		
		return "/alquiler/mostrarMisAlquileres";
	}
}
