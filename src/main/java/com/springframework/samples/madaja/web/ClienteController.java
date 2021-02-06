package com.springframework.samples.madaja.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.ReservaService;
import com.springframework.samples.madaja.service.SearchService;
import com.springframework.samples.madaja.service.VentaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ClienteController {
	
	private final ClienteService clienteService;
	
	private final VentaService ventaService;
	
	private final ReservaService reservaService;
	
	private final AlquilerService alquilerService;
	
	private final SearchService searchService;
	
	@Autowired
	public ClienteController(ClienteService clienteService, VentaService ventaService, ReservaService reservaService,AlquilerService alquilerService,SearchService searchService) {
		this.clienteService = clienteService;
		this.ventaService = ventaService;
		this.reservaService = reservaService;
		this.alquilerService = alquilerService;
		this.searchService = searchService;
	}
	

	@GetMapping(value = { "/clientes" })
	public String findAll(@RequestParam Map<String, Object> params, ModelMap model){
		
		//equivalente a un if/else, si se cumple, hace lo de la izq de los ":" y si no, lo de la derecha:
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1 ) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 10);
		
		Page<Cliente> pageCliente = this.clienteService.getAll(pageRequest);
		
		if(pageCliente == null) {
			log.warn("No se han podido encontrar ningún cliente");
		}
		
		int totalPage = pageCliente.getTotalPages();
		if(totalPage > 0) {
			// lista con todas las páginas que hay:
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}

		model.addAttribute("clientes", pageCliente.getContent());
		model.addAttribute("current", page+1);
		model.addAttribute("next", page+2);
		model.addAttribute("prev", page);
		model.addAttribute("max", totalPage);
				
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
	
	@GetMapping(value= {"/searchClientes"})
	public String initFindForm(ModelMap model) {
		model.put("cliente", new Cliente());
		return "cliente/mostrarClientes";
	}
	
	@PostMapping(value = {"/doSearchClientes"})
	public String searchClientes(@RequestParam(value="search",required = false) String searchText, ModelMap model) {
		
		if(searchText == "") {
			return "redirect:/clientes";
		}
		model.put("clientes", searchService.searchClientes(searchText));
		
		return "cliente/mostrarClientes";
	}
}
