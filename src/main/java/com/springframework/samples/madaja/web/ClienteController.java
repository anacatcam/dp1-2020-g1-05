package com.springframework.samples.madaja.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Reserva;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.ReservaService;
import com.springframework.samples.madaja.service.VentaService;

@Controller
public class ClienteController {
	
	private final ClienteService clienteService;
	
	@Autowired
	private final VentaService ventaService;
	
	@Autowired
	private final ReservaService reservaService;
	
	@Autowired
	private final AlquilerService alquilerService;
	
	@Autowired
	public ClienteController(ClienteService clienteService, VentaService ventaService, ReservaService reservaService,AlquilerService alquilerService) {
		this.clienteService = clienteService;
		this.ventaService = ventaService;
		this.reservaService = reservaService;
		this.alquilerService = alquilerService;
	}
	
	@GetMapping(value = "/clientes")
	public String showClienteList(ModelMap model) {
		Iterable<Cliente> clientes = this.clienteService.findAllClientes();
		model.put("clientes", clientes);
		return "cliente/mostrarClientes";
	}
	
	@GetMapping(value = {"/clientes/ventas/{clienteId}"})
	public String showVentasCliente(@PathVariable("clienteId") String id,ModelMap model) {
		Cliente cliente = this.clienteService.findClienteByDni(id);
		List<Venta> ventas = this.ventaService.findVentasByDni(cliente.getDni());
		model.put("cliente", cliente);
		model.put("ventas", ventas);
		return "cliente/ventaDetails";
	}
	@GetMapping(value = {"/clientes/alquileres/{clienteId}"})
	public String showAlquilerCliente(@PathVariable("clienteId") String id,ModelMap model) {
		Cliente cliente = this.clienteService.findClienteByDni(id);
		List<Alquiler> alquileres = this.alquilerService.findAlquilerByDni(cliente.getDni());
		model.put("cliente", cliente);
		model.put("alquileres", alquileres);
		return "cliente/alquilerDetails";
	}
}
