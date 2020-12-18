package com.springframework.samples.madaja.web;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.OfertaService;
import com.springframework.samples.madaja.service.VehiculosService;

@Controller
public class OfertaController {
	
	private static final String VIEW_OFERTA_CREATE_FORM = "oferta/createOfertaForm";
	private static final String VIEW_OFERTA_UPDATE_FORM = "oferta/updateOfertaForm";
	
	private final OfertaService ofertaService;
	private final VehiculosService vehiculosService;
	
	public OfertaController(OfertaService ofertaService, VehiculosService vehiculosService) {
		this.ofertaService=ofertaService;
		this.vehiculosService =vehiculosService;
	}
	
	@GetMapping(value = {"/oferta"})
	public String showOfertasList(Map<String, Object> model) {
		Collection<Oferta> ofertas = this.ofertaService.findAllOfertas();
		model.put("ofertas", ofertas);
		return "oferta/mostrarOfertas";
	}
	
	@GetMapping(value = {"/oferta/{ofertaId}"})
	public String showOferta(@PathVariable("ofertaId") int ofertaId, ModelMap map) {
		Oferta oferta = this.ofertaService.findOfertaById(ofertaId);
		List<Vehiculos> vehiculos = vehiculosService.findByOferta(ofertaId).stream().collect(Collectors.toList());
		map.put("oferta", oferta);
		map.put("vehiculos", vehiculos);
		return "oferta/ofertaDetails";
	}
	
	@InitBinder("oferta")
	public void initVehiculoBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	
	@ModelAttribute("matriculas")
	public List<String> matriculasDisponible(){
		Collection<Vehiculos> vehiculos = vehiculosService.findAllVehiculosDisponiblesYsinOfertas();
		List<String> matriculas = vehiculos.stream().map(x -> x.getMatricula()).collect(Collectors.toList());
		return matriculas;
	}
	
	@GetMapping(value = "/oferta/new")
	public String creationFormOferta(ModelMap model) {
		Oferta oferta = new Oferta();
		model.put("oferta", oferta);
		return VIEW_OFERTA_CREATE_FORM;
	}
	
	@PostMapping(value = "/oferta/new")
	public String processFormOferta(@Valid Oferta oferta, BindingResult result, ModelMap model, @RequestParam("matriculas") List<String> matriculas) {
		if(result.hasErrors()) {
			model.put("oferta", oferta);
			return VIEW_OFERTA_CREATE_FORM;
		}
		else {
			if(matriculas.get(0).compareTo("none") == 0) {
				this.ofertaService.saveOferta(oferta);
			}else {
				matriculas.remove("none");
				this.ofertaService.saveOferta(oferta);
				for(String matricula:matriculas) {
					Vehiculos vehiculo = vehiculosService.findByMatricula(matricula);
					vehiculo.setOferta(oferta);
					this.vehiculosService.saveVehiculo(vehiculo);
				}
			}
			return "redirect:/oferta";
		}
	}

	@GetMapping(value = "/oferta/{ofertaId}/edit")
	public String updateFormOferta(@PathVariable("ofertaId") int ofertaId, ModelMap model) {
		Oferta oferta = this.ofertaService.findOfertaById(ofertaId);
		List<Vehiculos> vehiculos = vehiculosService.findByOferta(ofertaId).stream().collect(Collectors.toList());
		for(Vehiculos vehiculo:vehiculos) {
			vehiculo.setOferta(null);
			vehiculosService.saveVehiculo(vehiculo);
		}
		model.put("oferta", oferta);
		model.put("vehiculos", vehiculos);
		return VIEW_OFERTA_UPDATE_FORM;
	}
	
	@PostMapping(value = "/oferta/{ofertaId}/edit")
	public String processUpdateFormOferta(@PathVariable("ofertaId") int ofertaId, ModelMap model, @Valid Oferta oferta, BindingResult result, @RequestParam("matriculas") List<String> matriculas) {
		if(result.hasErrors()) {
			model.put("oferta", oferta);
			List<Vehiculos> vehiculos = vehiculosService.findByOferta(ofertaId).stream().collect(Collectors.toList());
			model.put("vehiculos", vehiculos);
			return VIEW_OFERTA_UPDATE_FORM;
		}else {
			Oferta ofertaUpdate = this.ofertaService.findOfertaById(ofertaId);
			BeanUtils.copyProperties(oferta, ofertaUpdate,"id");
			if(matriculas.get(0).compareTo("none") == 0) {
				this.ofertaService.saveOferta(ofertaUpdate);
			}else {
				matriculas.remove("none");
				this.ofertaService.saveOferta(ofertaUpdate);
				for(String matricula:matriculas) {
					Vehiculos vehiculo = vehiculosService.findByMatricula(matricula);
					vehiculo.setOferta(ofertaUpdate);
					this.vehiculosService.saveVehiculo(vehiculo);
				}
			}
			return "redirect:/oferta";
		}
	}
	
	@GetMapping(value = "/oferta/{ofertaId}/delete")
	public String deleteOferta(@PathVariable("ofertaId") int ofertaId, ModelMap map) {
		this.ofertaService.deleteById(ofertaId);
		return "redirect:/oferta";
	}
}
