package com.springframework.samples.madaja.web;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springframework.samples.madaja.model.SeguroCliente;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.SeguroClienteService;
import com.springframework.samples.madaja.service.VehiculosService;

@Controller
@RequestMapping("/vehiculos/{vehiculoId}")
public class SeguroClienteController {
	
	private static final String VIEWS_SEGURO_CLIENTE_CREATE_FORM = "seguroCl/createSeguroClForm";
	private static final String VIEWS_SEGURO_CLIENTE_UPDATE_FORM = "seguroCl/updateSeguroClForm";
	
	private final SeguroClienteService seguroClienteService;
	private final VehiculosService vehiculosService;
	
	@Autowired
	public SeguroClienteController(SeguroClienteService seguroClienteService, VehiculosService vehiculosService) {
		this.seguroClienteService=seguroClienteService;
		this.vehiculosService=vehiculosService;
	}
	
	@ModelAttribute("vehiculos")
	public Vehiculos findVehiculo(@PathVariable("vehiculoId") int vehiculoId) {
		return this.vehiculosService.findVehiculoById(vehiculoId);
	}
	
	@InitBinder("vehiculos")
	public void initVehiculoBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/seguroCliente/new")
	public String initCreationForm(Vehiculos vehiculo, ModelMap model) {
		SeguroCliente seguroCliente = new SeguroCliente();
		vehiculo.addSeguroCliente(seguroCliente);
		model.put("seguroCliente", seguroCliente);
		return VIEWS_SEGURO_CLIENTE_CREATE_FORM;
	}

	@PostMapping(value = "/seguroCliente/new")
	public String processCreationForm(Vehiculos vehiculo, @Valid SeguroCliente seguroCliente,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("seguroCliente", seguroCliente);
			return VIEWS_SEGURO_CLIENTE_CREATE_FORM;
		}
		else {
			vehiculo.addSeguroCliente(seguroCliente);
        	this.seguroClienteService.saveSeguroCliente(seguroCliente);
			return "redirect:/vehiculos/{vehiculoId}";
		}
	}
	
	@GetMapping(value = "/seguroCliente/{seguroClienteId}/edit")
	public String initUpdateForm(@PathVariable("seguroClienteId") int seguroClienteId, ModelMap model) {
		SeguroCliente seguroCliente = this.seguroClienteService.findSeguroClienteById(seguroClienteId);
		model.put("seguroCliente", seguroCliente);
		return VIEWS_SEGURO_CLIENTE_UPDATE_FORM;
	}
	
    @PostMapping(value = "/seguroCliente/{seguroClienteId}/edit")
	public String processUpdateForm(@Valid SeguroCliente seguroCliente, BindingResult result,
			Vehiculos vehiculo,@PathVariable("seguroClienteId") int seguroClienteId, ModelMap model) {
		if (result.hasErrors()) {
			model.put("seguroCliente", seguroCliente);
			return VIEWS_SEGURO_CLIENTE_UPDATE_FORM;
		}
		else {
			SeguroCliente seguroClienteToUpdate = this.seguroClienteService.findSeguroClienteById(seguroClienteId);
			BeanUtils.copyProperties(seguroCliente, seguroClienteToUpdate, "id","vehiculos");                                                                                  
			this.seguroClienteService.saveSeguroCliente(seguroClienteToUpdate);                    
			return "redirect:/vehiculos/{vehiculoId}";
		}
	}
    
    
    @GetMapping(value = "/seguroCliente/{seguroClienteId}/delete")
	public String deleteSeguroCliente(@PathVariable("seguroClienteId") int seguroClienteId, ModelMap map) {
		this.seguroClienteService.deleteById(seguroClienteId);
		return "redirect:/vehiculos/{vehiculoId}";
	}

}
