package com.springframework.samples.madaja.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.service.OfertaService;

@Controller
public class OfertaController {
	
	private final OfertaService ofertaService;
	
	public OfertaController(OfertaService ofertaService) {
		this.ofertaService=ofertaService;
	}
	
	@GetMapping(value = {"/oferta"})
	public String showOfertasList(Map<String, Object> model) {
		Collection<Oferta> ofertas = this.ofertaService.findAllOfertas();
		model.put("ofertas", ofertas);
		return "oferta/mostrarOfertas";
	}
	
	@GetMapping(value = {"/oferta/{ofertaId}"})
	public ModelAndView showOferta(@PathVariable("ofertaId") int ofertaId) {
		ModelAndView mav = new ModelAndView("oferta/ofertaDetails");
		mav.addObject(this.ofertaService.findOfertaById(ofertaId));
		return mav;
	}

}
