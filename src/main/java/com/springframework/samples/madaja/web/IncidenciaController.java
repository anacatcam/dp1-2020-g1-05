package com.springframework.samples.madaja.web;



import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.IncidenciaService;
import com.springframework.samples.madaja.service.VehiculosService;

@Controller
@RequestMapping("/vehiculos/{vehiculoId}")
public class IncidenciaController {
	
	private static final String VIEWS_INCIDENCIA_CREATE_FORM = "incidencia/createIncidenciaForm";
	private static final String VIEWS_INCIDENCIA_UPDATE_FORM = "incidencia/updateIncidenciaForm";

	private final IncidenciaService incidenciaService;
	private final VehiculosService vehiculosService;
	private final ClienteService clienteService;

	
	@Autowired
	public IncidenciaController(IncidenciaService incidenciaService, VehiculosService vehiculosService,
			ClienteService clienteService) {
		this.incidenciaService=incidenciaService;
		this.vehiculosService=vehiculosService;
		this.clienteService=clienteService;
	}
	
	@ModelAttribute("vehiculos")
	public Vehiculos findVehiculo(@PathVariable("vehiculoId") int vehiculoId) {
		return this.vehiculosService.findVehiculoById(vehiculoId);
	}
	
	@InitBinder("vehiculos")
	public void initVehiculoBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/incidencia/new")
	public String initCreationForm(Vehiculos vehiculo, ModelMap model) {
		Incidencia incidencia = new Incidencia();
		vehiculo.addIncidencia(incidencia);
		model.put("incidencia", incidencia);
		return VIEWS_INCIDENCIA_CREATE_FORM;
	}

	@PostMapping(value = "/incidencia/new")
	public String processCreationForm(Vehiculos vehiculo, @Valid Incidencia incidencia,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("incidencia", incidencia);
			return VIEWS_INCIDENCIA_UPDATE_FORM;
		}
		else {
    		incidencia.setSolucionada(false); //la incidencia no está solucionada por defecto
			vehiculo.addIncidencia(incidencia);
			
			//Obtener cliente para sumarle la incidencia
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			
			if(principal instanceof UserDetails) {
				 username = ((UserDetails)principal).getUsername();
			}else {
				 username = principal.toString();
			}
			Cliente cliente = this.clienteService.findClienteByUsername(username);
			cliente.addIncidencia(incidencia);
			
			//comprobación de si el cliente pasa a ser conflictivo
			if(cliente.getIncidencias().size() == 5) {
				cliente.setEsConflictivo("Si");
			}
			
        	this.incidenciaService.saveIncidencia(incidencia);
			return "redirect:/vehiculos/{vehiculoId}";
		}
	}
	
	@GetMapping(value = "/incidencia/{incidenciaId}/edit")
	public String initUpdateForm(@PathVariable("incidenciaId") int incidenciaId, ModelMap model) {
		Incidencia incidencia = this.incidenciaService.findIncidenciaById(incidenciaId);
		model.put("incidencia", incidencia);
		return VIEWS_INCIDENCIA_UPDATE_FORM;
	}
	
    @PostMapping(value = "/incidencia/{incidenciaId}/edit")
	public String processUpdateForm(@Valid Incidencia incidencia, BindingResult result,
			Vehiculos vehiculo,@PathVariable("incidenciaId") int incidenciaId, ModelMap model) {
		if (result.hasErrors()) {
			model.put("incidencia", incidencia);
			return VIEWS_INCIDENCIA_UPDATE_FORM;
		}
		else {
			Incidencia incidenciaToUpdate = this.incidenciaService.findIncidenciaById(incidenciaId);
			BeanUtils.copyProperties(incidencia, incidenciaToUpdate, "id","vehiculos","mecanicos");                                                                                  
			this.incidenciaService.saveIncidencia(incidenciaToUpdate);                    
			return "redirect:/vehiculos/{vehiculoId}";
		}
	}

}
