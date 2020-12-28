package com.springframework.samples.madaja.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.VehiculosService;

@Controller
public class AlquilerController {
	
	private static final String VIEWS_ALQUILER_CREATE_FORM = "alquiler/createAlquilerForm";

	private final AlquilerService alquilerService;
	
	private final ClienteService clienteService;
	
	private final VehiculosService vehiculosService;
	
	@Autowired
	public AlquilerController(AlquilerService alquilerService,ClienteService clienteService, 
			VehiculosService vehiculosService) {
		this.alquilerService = alquilerService;
		this.clienteService = clienteService;
		this.vehiculosService = vehiculosService;
	}
	
	@InitBinder("alquiler")
	public void initVehiculoBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder
	public void setValidator(WebDataBinder dataBinder) {
		dataBinder.setValidator(new AlquilerValidator());
	}
	
	@GetMapping(value = {"/MisAlquileres"})
	public String showMisAlquileres(/*@PathVariable("clienteDni") String dni,*/ModelMap model){
		List<Alquiler> alqList = new ArrayList<>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
		}else {
			 username = principal.toString();
		}
		Cliente cliente = this.clienteService.findClienteByUsername(username);
		
	//	Cliente cliente = this.clienteService.findClienteByDni(dni);
		alqList.addAll(this.alquilerService.findAlquilerByDni(cliente.getDni()));
		model.put("alquileres", alqList);
		
		
		return "/alquiler/mostrarMisAlquileres";
	}
	
	@GetMapping(value = "/vehiculos/{vehiculoId}/alquilar")
	public String initAlquilarVehiculo(@PathVariable("vehiculoId") int vehiculoId, Map<String, Object> model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
		}else {
			 username = principal.toString();
		}
		
		//Obtener cliente logueado y vehiculo
		Cliente cliente = this.clienteService.findClienteByUsername(username);
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		vehiculo.setDisponible(this.vehiculosService.findDisponibleById(5));
		
		//Crear alquiler
		Alquiler nuevoAlquiler = new Alquiler();
		nuevoAlquiler.setCliente(cliente);
		nuevoAlquiler.setVehiculo(vehiculo);
		nuevoAlquiler.setReserva(null);
		nuevoAlquiler.setDepLleno(true);
		nuevoAlquiler.setEnvio(null);
		nuevoAlquiler.setRecogida(null);
		nuevoAlquiler.setEnvio(null);
		nuevoAlquiler.setSeguro_cliente(null);
		model.put("alquiler", nuevoAlquiler);
		
		return VIEWS_ALQUILER_CREATE_FORM;
	}
	
	@PostMapping(value = "/vehiculos/{vehiculoId}/alquilar")
	public String processAlquilarVehiculo(@PathVariable("vehiculoId") int vehiculoId, @Valid Alquiler alquiler, 
			BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_ALQUILER_CREATE_FORM;
		}
		else {
			alquilerService.saveAlquiler(alquiler);

			return "redirect:/MisAlquileres";
		}
	}
	
}
