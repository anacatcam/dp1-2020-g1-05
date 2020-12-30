package com.springframework.samples.madaja.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.service.VentaService;

@Controller
public class VentaController {
	
	private final VentaService ventaService;
	
	private final ClienteService clienteService;
	
	private final VehiculosService vehiculosService;
	
	@Autowired
	public VentaController(VentaService ventaService, ClienteService clienteService, VehiculosService vehiculosService) {
		this.ventaService = ventaService;
		this.clienteService = clienteService;
		this.vehiculosService = vehiculosService;
	}
	
	@GetMapping(value = {"/MisVentas"})
	public String showMisVentas(/*@PathVariable("clienteDni") String dni,*/ModelMap model){
		List<Venta> venVist = new ArrayList<>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
		}else {
			 username = principal.toString();
		}
		Cliente cliente = this.clienteService.findClienteByUsername(username);
		
	//	Cliente cliente = this.clienteService.findClienteByDni(dni);
		venVist.addAll(this.ventaService.findVentasByDni(cliente.getDni()));
		model.put("ventas", venVist);
		
		return "/venta/mostrarMisVentas";
	}
	
	@GetMapping(value = "/vehiculos/{vehiculoId}/comprar")
	public String comprarVehiculo(@PathVariable("vehiculoId") int vehiculoId,
			Map<String, Object> model) {
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
		vehiculo.setDisponible(this.vehiculosService.findDisponibleById(6));
		
		//Crear venta
		Venta nuevaVenta = new Venta();
		nuevaVenta.setFecha(LocalDate.now());
		nuevaVenta.setCliente(cliente);
		nuevaVenta.setVehiculo(vehiculo);
		nuevaVenta.setReserva(null);
		ventaService.saveVenta(nuevaVenta);
		
		return "redirect:/MisVentas";
	}

}
