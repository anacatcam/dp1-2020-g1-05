package com.springframework.samples.madaja.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springframework.samples.madaja.model.Reserva;
import com.springframework.samples.madaja.service.ReservaService;

@Controller
@RequestMapping("/reservas")
public class ReservasController {
	
	@Autowired
	private ReservaService reservaService;
	
	@GetMapping()
	public String listadoMisReservas(ModelMap modelMap) {
		String vista = ("/reservas/mostrarReservas");
		Iterable<Reserva> reservas = reservaService.findAllReserva();
		modelMap.addAttribute("reservas", reservas);
		return vista;
	}
	
	/** Reservas de un cliente **/
	@GetMapping(path="/{dniCliente}")
	public String reservasCliente(@PathVariable("dniCliente") int dniCliente, ModelMap modelMap) {
		String view = "reservas/mostrarReservas";
		Iterable<Reserva> reservas = reservaService.findByDNI(dniCliente);
		modelMap.addAttribute("reservas", reservas);
		return view;
	}
	
	
	
	@GetMapping(path="/delete/{reservaId}")
	public String borrarReserva(@PathVariable("reservaId") int reservaId, ModelMap modelMap) {
		
		String view = "reservas/mostrarReservas";
		Optional<Reserva> reserva = reservaService.findReservaById(reservaId);
		if(reserva.isPresent()) {
			reservaService.delete(reserva.get());
			/*
			Iterable<Reserva> reservas = reservaService.findAllReserva();
			modelMap.addAttribute("reservas", reservas);
			*/
			modelMap.addAttribute("message", "Reserva anulada correctamente");
		} else {
			modelMap.addAttribute("message", "Reserva no encontrada");
		}
		return view;
	}
		
}
