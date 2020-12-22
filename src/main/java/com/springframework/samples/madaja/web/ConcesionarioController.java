package com.springframework.samples.madaja.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.service.ConcesionarioService;

@Controller
public class ConcesionarioController {
	
	private final ConcesionarioService concesionarioService;
	
	@Autowired
	public ConcesionarioController(ConcesionarioService concesionarioService) {
		this.concesionarioService=concesionarioService;
	}
	
	@GetMapping(value = { "/concesionario" })
	public String showConcesionariosList(Map<String, Object> model) {
		Collection<Concesionario> concesionarios = this.concesionarioService.findAllConcesionarios();
		model.put("concesionarios", concesionarios);
		return "concesionario/mostrarConcesionarios";
	}
	
	@GetMapping("/concesionario/{concesionarioId}")
	public String showConcesionario(@PathVariable("concesionarioId") int concesionarioId, ModelMap model) {
		Concesionario concesionario = this.concesionarioService.findConcesionarioById(concesionarioId);
		model.put("concesionario", concesionario);
		return "concesionario/concesionarioDetails";
	}

}
