package com.springframework.samples.madaja.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Incidencia;
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
	public String showMisVentas(ModelMap model){
		List<Venta> venVist = new ArrayList<>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
		}else {
			 username = principal.toString();
		}
		Cliente cliente = this.clienteService.findClienteByUsername(username);
		
		venVist.addAll(this.ventaService.findVentasByDni(cliente.getDni()));
		model.put("ventas", venVist);
		
		return "/venta/mostrarMisVentas";
	}
	
	@GetMapping(value = {"/ventas"})
	public String mostrarVentas(ModelMap modelMap) {
		String vista = ("/venta/mostrarVentas");
		Iterable<Venta> ventas = ventaService.findAllVentas();
		modelMap.addAttribute("ventas", ventas);
		
		return vista;
	}

	
	@GetMapping(value = "/vehiculos/{vehiculoId}/comprar")
	public String comprarVehiculo(@PathVariable("vehiculoId") int vehiculoId, Map<String, Object> model) {
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		Boolean vendido  = estaVendido(vehiculo);
		if(vendido) {
			model.put("esVenta", vendido);
			return "operacionImposible";
		}else if(estaEnRevision(vehiculo)){
			model.put("enRevision", estaEnRevision(vehiculo));
			model.put("esRevisionVenta", true);
			return "operacionImposible";
		}else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			if(principal instanceof UserDetails) {
				 username = ((UserDetails)principal).getUsername();
			}else {
				 username = principal.toString();
			}
			
			//Obtener cliente logueado y vehiculo
			Cliente cliente = this.clienteService.findClienteByUsername(username);
			vehiculo.setDisponible(this.vehiculosService.findDisponibleById(5));
			this.vehiculosService.saveVehiculo(vehiculo);
			
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
	
	public boolean estaVendido(Vehiculos vehiculo) {
		Boolean res = false;
		Iterable<Venta> ventas = this.ventaService.findAllVentas();
		for(Venta v:ventas) {
			if(vehiculo.equals(v.getVehiculo())) {
				res = true;
				return res;
			}
		}
		return res;
	}
	
	public boolean estaEnRevision(Vehiculos vehiculo) {
		Boolean res = false;
		List<Incidencia> incidencias = vehiculo.getIncidencias();
		for(Incidencia i:incidencias) {
			if(i.getSolucionada() == false) {
				res = true;
				break;
			}
		}
		return res;
	}

}
