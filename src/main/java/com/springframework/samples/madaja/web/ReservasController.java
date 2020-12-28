package com.springframework.samples.madaja.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Reserva;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.ReservaService;
import com.springframework.samples.madaja.service.VentaService;

@Controller
@RequestMapping("/reservas")
public class ReservasController {
	
	@Autowired
	private ReservaService reservaService;
	@Autowired
	private VentaService ventaService;
	@Autowired
	private AlquilerService alquilerService;
	
	private final ClienteService clienteService;
	
	@Autowired
	public ReservasController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping(path="/new")
	public String crearReserva(ModelMap modelMap) {
		Reserva reserva = new Reserva();
		modelMap.put("reserva", reserva);		
		return "reservas/mostrarReservas";
	}
	
	@PostMapping(path="/new")
	public String guardarReserva(@Valid Reserva reserva, BindingResult result, ModelMap modelMap) {
		if(result.hasErrors()) {
			modelMap.put("reserva", reserva);
			return "reservas/editReservaForm";
		} else {
			this.reservaService.save(reserva);
			modelMap.addAttribute("message", reserva);
			return "reservas/mostrarReservas";
		}
		
		//return "redirect:/reservas";
	}
	
	/** Todas las reservas (vista del administrador) **/
	@GetMapping()
	public String mostrarReservas(ModelMap modelMap) {
		String vista = ("/reservas/mostrarReservas");
		Iterable<Venta> ventas = ventaService.findAllVentas();
		modelMap.addAttribute("ventas", ventas);
		Iterable<Alquiler> alquileres = alquilerService.findAllAlquiler();
		modelMap.addAttribute("alquileres", alquileres);
		
		return vista;
	}
	
	/** Mis reservas (reservas del usuario autenticado) **/
	@GetMapping(value = {"/mis-reservas"})
	public String mostrarMisReservas(ModelMap modelMap) {
		String vista = ("/reservas/mostrarMisReservas");
		
		/** Para pasar las reservas en vez de alquileres y ventas
		List<Reserva> resList = new ArrayList<>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
		}else {
			 username = principal.toString();
		}
		Cliente cliente = this.clienteService.findClienteByUsername(username);
						
		resList.addAll(this.reservaService.findByDNI(cliente.getDni()));
		modelMap.put("reservas", resList);
		**/
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
		}else {
			 username = principal.toString();
		}
		Cliente cliente = this.clienteService.findClienteByUsername(username);
		
		List<Alquiler> alqList = new ArrayList<>();
		List<Venta> venList = new ArrayList<>();
		alqList.addAll(this.alquilerService.findAlquilerByDni(cliente.getDni()));
		venList.addAll(this.ventaService.findVentasByDni(cliente.getDni()));
		modelMap.put("alquileres", alqList);
		modelMap.put("ventas", venList);
		
		return vista;
	}
	
	/** OBSOLETO **/
	/** Reservas de un cliente **/
	@GetMapping(path="/{dniCliente}")
	public String reservasCliente(@PathVariable("dniCliente") String dniCliente, ModelMap modelMap) {
		String view = "reservas/mostrarReservas";
		Iterable<Reserva> reservas = reservaService.findByDNI(dniCliente);
		modelMap.addAttribute("reservas", reservas);
		return view;
	}
	
	/** Anular reservas admin **/
	@GetMapping(path = "/delete/{reservaId}")
	public String borrarReserva(@PathVariable("reservaId") int reservaId, ModelMap modelMap) {
		String view = "";
		Optional<Reserva> reserva = reservaService.findReservaById(reservaId);
		if (reserva.isPresent()) {
			reservaService.deleteRes(reservaId);
			view = "reservas/mostrarReservas";
			mostrarReservas(modelMap);
			modelMap.addAttribute("message", "Reserva anulada correctamente");
		} else {
			modelMap.addAttribute("message", "Reserva no encontrada");
		}
		return view;
	}
	
	/** Anular reserva usuario **/
	@GetMapping(path = "/deleteU/{reservaId}")
	public String borrarMiReserva(@PathVariable("reservaId") int reservaId, ModelMap modelMap) {
		String view = "";

		Optional<Reserva> reserva = reservaService.findReservaById(reservaId);
		if (reserva.isPresent()) {
			reservaService.deleteRes(reservaId);
			view = "reservas/mostrarMisReservas";
			mostrarMisReservas(modelMap);
			modelMap.addAttribute("message", "Reserva anulada correctamente");
		} else {
			modelMap.addAttribute("message", "Reserva no encontrada");
		}
		return view;
	}
	
}
