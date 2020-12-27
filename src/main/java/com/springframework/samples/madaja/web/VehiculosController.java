package com.springframework.samples.madaja.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.IncidenciaService;
import com.springframework.samples.madaja.service.VehiculosService;

@Controller
public class VehiculosController {
	
	private static final String VIEWS_VEHICULOS_CREATE_OR_UPDATE_FORM = "vehiculos/createOrUpdateVehiculoForm";
	
	private final VehiculosService vehiculosService;
//	private final IncidenciaService incidenciaService;
//	private final SeguroVehiculoService seguroVehiculoService;

		
	@Autowired
	public VehiculosController(VehiculosService vehiculosService/*, IncidenciaService incidenciaService,
			SeguroVehiculoService seguroVehiculoService*/) {
		this.vehiculosService=vehiculosService;
//		this.incidenciaService=incidenciaService;
//		this.seguroVehiculoService=seguroVehiculoService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	

	@GetMapping(value = { "/vehiculos" })
	public String showVehiculosList(Map<String, Object> model) {
		Collection<Vehiculos> vehiculos = this.vehiculosService.findAllVehiculos();
		Collection<Disponible> disponible = this.vehiculosService.findAllDisponibles();
		model.put("vehiculos", vehiculos);
		model.put("disponible", disponible);
		return "vehiculos/mostrarVehiculos";
	}
	
	@GetMapping(value = "/vehiculos/new")
	public String initCreationForm(Map<String, Object> model) {
		Vehiculos vehiculo = new Vehiculos();
		model.put("vehiculos", vehiculo);
		model.put("cambios", this.vehiculosService.findAllCambios());
		model.put("concesionarios", this.vehiculosService.findAllConcesionarios());
		model.put("disponibles", this.vehiculosService.findAllDisponibles());
		model.put("combustibles", this.vehiculosService.findAllCombustibles());
		model.put("seguros", this.vehiculosService.findAllSeguros());
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
	}   //ESTO AL FINAL NO ME RECOGE EL VALOR DE CAMBIO Y MALETERO
	
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
	public String initUpdateForm(@PathVariable("vehiculoId") int vehiculoId, ModelMap model) {
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		model.put("vehiculos", vehiculo);
		model.put("cambios", this.vehiculosService.findAllCambios());
		model.put("concesionarios", this.vehiculosService.findAllConcesionarios());
		model.put("disponibles", this.vehiculosService.findAllDisponibles());
		model.put("combustibles", this.vehiculosService.findAllCombustibles());
		model.put("seguros", this.vehiculosService.findAllSeguros());
		return VIEWS_VEHICULOS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/vehiculos/{vehiculoId}/edit")
	public String processUpdateForm(@Valid Vehiculos vehiculo, BindingResult result,
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
	
	@GetMapping(value = "/vehiculos/{vehiculoId}")
	public ModelAndView showVehiculo(@PathVariable("vehiculoId") int vehiculoId) {
		ModelAndView mav = new ModelAndView("vehiculos/vehiculoDetails");
		mav.addObject(this.vehiculosService.findVehiculoById(vehiculoId));
		return mav;
	}
	
	@GetMapping(value = "/vehiculos/{vehiculoId}/delete")
	public String deleteVehiculo(@PathVariable("vehiculoId") int vehiculoId, Map<String, Object> model) {
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		Disponible disponible = this.vehiculosService.findDisponibleById(4);
		vehiculo.setDisponible(disponible);
		this.vehiculosService.saveVehiculo(vehiculo);
		return "redirect:/vehiculos";
	}
	
	@GetMapping(value="/vehiculos/disponible/{disponibleId}")
	public String showVehiculosDisponibleList(@PathVariable("disponibleId") int disponibleId, 
			Map<String, Object> model) {
		Collection<Vehiculos> vehiculos = this.vehiculosService.findByDisponible(disponibleId);
		Collection<Disponible> disponible = this.vehiculosService.findAllDisponibles();
		model.put("vehiculos", vehiculos);
		model.put("disponible", disponible);
		return "vehiculos/mostrarVehiculos";
	}

}
