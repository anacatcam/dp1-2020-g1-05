package com.springframework.samples.madaja.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.VehiculosService;

@Controller
public class VehiculosController {
	
	private static final String VIEWS_VEHICULOS_CREATE_OR_UPDATE_FORM = "vehiculos/createOrUpdateVehiculoForm";
	
	private final VehiculosService vehiculosService;
	
	public VehiculosController(VehiculosService vehiculosService) {
		this.vehiculosService=vehiculosService;
	}
	
	@GetMapping(value = { "/vehiculos" })
	public String showVehiculosList(Map<String, Object> model) {
		Collection<Vehiculos> vehiculos = this.vehiculosService.findAllVehiculos();
		model.put("vehiculos", vehiculos);
		return "vehiculos/mostrarVehiculos";
		
	}
	
	@GetMapping(value = "/vehiculos/{vehiculoId}/edit")
	public String initUpdateVehiculoForm(@PathVariable("vehiculoId") int vehiculoId, Model model) {
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		model.addAttribute(vehiculo);
		return VIEWS_VEHICULOS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/vehiculos/{vehiculoId}/edit")
	public String processUpdateVehiculoForm(@Valid Vehiculos vehiculo, BindingResult result,
			@PathVariable("vehiculoId") int vehiculoId) {
		if (result.hasErrors()) {
			return VIEWS_VEHICULOS_CREATE_OR_UPDATE_FORM;
		}
		else {
			vehiculo.setId(vehiculoId);
			this.vehiculosService.saveVehiculo(vehiculo);
			return "redirect:/vehiculos/{vehiculoId}";
		}
	}
	
	@GetMapping("/vehiculos/{vehiculoId}")
	public ModelAndView showVehiculo(@PathVariable("vehiculoId") int vehiculoId) {
		ModelAndView mav = new ModelAndView("vehiculos/vehiculoDetails");
		mav.addObject(this.vehiculosService.findVehiculoById(vehiculoId));
		return mav;
	}

}
