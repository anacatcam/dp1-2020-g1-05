package com.springframework.samples.madaja.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springframework.samples.madaja.model.Reserva;
import com.springframework.samples.madaja.service.ReservaService;

@Controller
@RequestMapping("/mis-reservas")
public class MisReservasController {
	
	@Autowired
	private ReservaService reservaService;
	
	@GetMapping()
	public String listadoMisReservas(ModelMap modelMap) {
		String vista = ("/mis-reservas/mostrarMisReservas");
		Iterable<Reserva> reservas = reservaService.findAllReserva();
		modelMap.addAttribute("reservas", reservas);
		return vista;
	}

	
	
}
