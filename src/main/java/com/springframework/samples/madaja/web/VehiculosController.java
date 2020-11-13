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

import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.IncidenciaService;
import com.springframework.samples.madaja.service.VehiculosService;

@Controller
public class VehiculosController {
	
	private static final String VIEWS_VEHICULOS_CREATE_OR_UPDATE_FORM = "vehiculos/createOrUpdateVehiculoForm";

	private static final String VIEWS_INCIDENCIA_CREATE_OR_UPDATE_FORM = "incidencia/createOrUpdateIncidenciaForm";
	
	private final VehiculosService vehiculosService;
	
	private final IncidenciaService incidenciaService;
	
	public VehiculosController(VehiculosService vehiculosService, IncidenciaService incidenciaService) {
		this.vehiculosService=vehiculosService;
		this.incidenciaService=incidenciaService;
	}
	
	@GetMapping(value = { "/vehiculos" })
	public String showVehiculosList(Map<String, Object> model) {
		Collection<Vehiculos> vehiculos = this.vehiculosService.findAllVehiculos();
		model.put("vehiculos", vehiculos);
		return "vehiculos/mostrarVehiculos";
	}
	
	@GetMapping(value = "/vehiculos/new")
	public String initCreationForm(Map<String, Object> model) {
		Vehiculos vehiculo = new Vehiculos();
		vehiculo.setDisponible(true); //el coche nuevo está disponible por defecto
		model.put("vehiculos", vehiculo);
		return VIEWS_VEHICULOS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/vehiculos/new")
	public String processCreationForm(@Valid Vehiculos vehiculo, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_VEHICULOS_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.vehiculosService.saveVehiculo(vehiculo);
			
			return "redirect:/vehiculos/" + vehiculo.getId();
		}
	}
	
//	@GetMapping(value = "/vehiculos/buscar")
//	public String processFindForm(Vehiculos vehiculo, BindingResult result, Map<String, Object> model) {
//
//		// permitir solicitudes GET sin parámetros
//		if (vehiculo.getPlazas() == null) {
//			vehiculo.setPlazas(0); // empty string signifies broadest possible search
//		}
//
//		// encontrar vehículos por número de plazas
//		Collection<Vehiculos> results = this.vehiculosService.findVehiculoByPlazas(vehiculo.getPlazas());
//		if (results.isEmpty()) {
//			// no se encontraron vehículos
//			result.rejectValue("vehículo", "notFound", "not found");
//			return "vehiculos/mostrarVehiculos";
//		}
//		else if (results.size() == 1) {
//			// si se encuentra 1 vehículo
//			vehiculo = results.iterator().next();
//			return "redirect:/vehiculos/" + vehiculo.getId();
//		}
//		else {
//			// si se encuentran más de 1
//			model.put("selections", results);
//			return "vehiculos/mostrarVehiculos";
//		}
//	}
	
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
	
	@GetMapping(value = "/vehiculos/{vehiculoId}/incidencia/{incidenciaId}/edit")
	public String initUpdateIncidenciaForm(@PathVariable("vehiculoId") int vehiculoId, 
			@PathVariable("incidenciaId") int incidenciaId, Model model) {
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		Incidencia incidencia = this.incidenciaService.findIncidenciaById(incidenciaId);
		model.addAttribute(vehiculo);
		model.addAttribute(incidencia);
		return VIEWS_INCIDENCIA_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/vehiculos/{vehiculoId}/incidencia/{incidenciaId}/edit")
	public String processUpdateIncidenciaForm(@Valid Incidencia incidencia, /*@Valid Vehiculos vehiculo, */
			BindingResult result, @PathVariable("vehiculoId") int vehiculoId, 
			@PathVariable("incidenciaId") int incidenciaId) {
		if (result.hasErrors()) {
			return VIEWS_INCIDENCIA_CREATE_OR_UPDATE_FORM;
		}
		else {
			incidencia.setId(incidenciaId);
			//vehiculo.addIncidencia(incidencia);
			this.incidenciaService.saveIncidencia(incidencia);
			return "redirect:/vehiculos/{vehiculoId}";
		}
	}  //ESTO DA UN FALLO QUE NO SE CUAL ES AL EDITAR LA INCIDENCIA
	
	//FALTA EL CONTROLADOR DE NUEVA INCIDENCIA

}
