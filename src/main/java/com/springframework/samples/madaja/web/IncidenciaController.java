package com.springframework.samples.madaja.web;



import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Mecanico;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.IncidenciaService;
import com.springframework.samples.madaja.service.MecanicoService;
import com.springframework.samples.madaja.service.VehiculosService;

@Controller
@RequestMapping("/vehiculos/{vehiculoId}")
public class IncidenciaController {
	
	private static final String VIEWS_INCIDENCIA_CREATE_FORM = "incidencia/createIncidenciaForm";
	private static final String VIEWS_INCIDENCIA_UPDATE_FORM = "incidencia/updateIncidenciaForm";

	private final IncidenciaService incidenciaService;
	private final VehiculosService vehiculosService;
	private final ClienteService clienteService;
	private final MecanicoService mecanicoService;

	
	@Autowired
	public IncidenciaController(IncidenciaService incidenciaService, VehiculosService vehiculosService,
			ClienteService clienteService, MecanicoService mecanicoService) {
		this.incidenciaService=incidenciaService;
		this.vehiculosService=vehiculosService;
		this.clienteService=clienteService;
		this.mecanicoService=mecanicoService;
	}
	
	@ModelAttribute("vehiculos")
	public Vehiculos findVehiculo(@PathVariable("vehiculoId") int vehiculoId) {
		return this.vehiculosService.findVehiculoById(vehiculoId);
	}
	
	@ModelAttribute("mecanicos")
	public List<Mecanico> mecanicosDisponibles(){
		List<Mecanico> mecanicos = mecanicoService.findAll();
		return mecanicos;
	}
	
	@ModelAttribute("clientes")
	public Iterable<Cliente> clientes(){
		Iterable<Cliente> clientes = clienteService.findAllClientes();
		return clientes;
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
			BindingResult result, ModelMap model, @RequestParam(name="mecanicos") Optional<String[]> mecanicos,
				@RequestParam(name="clientes") Optional<String[]> clientes) {
		if (result.hasErrors()) {
			model.put("incidencia", incidencia);
			return VIEWS_INCIDENCIA_UPDATE_FORM;
		}
		else {
    		incidencia.setSolucionada(false); //la incidencia no está solucionada por defecto
			vehiculo.addIncidencia(incidencia);
			
			if(!clientes.isEmpty()) {
				for(String clienteId : clientes.get()) {
					Cliente cliente = clienteService.findClienteById(Integer.parseInt(clienteId));
					cliente.addIncidencia(incidencia);
					//comprobación de si el cliente pasa a ser conflictivo
					if(cliente.getIncidencias().size() == 5) {
						cliente.setEsConflictivo("Si");
					}
				}
			}
			
			if(!mecanicos.isEmpty()) {
				for(String mecanicoDni : mecanicos.get()) {
					Mecanico mecanico = mecanicoService.findById(mecanicoDni);
					mecanico.addIncidencia(incidencia);
					incidencia.addMecanico(mecanico);
					mecanicoService.saveMecanico(mecanico);
				}
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
			Vehiculos vehiculo,@PathVariable("incidenciaId") int incidenciaId, ModelMap model, 
				@RequestParam(name="mecanicos") Optional<String[]> mecanicos, 
				@RequestParam(name="clientes") Optional<String[]> clientes) {
		if (result.hasErrors()) {
			model.put("incidencia", incidencia);
			return VIEWS_INCIDENCIA_UPDATE_FORM;
		}
		else {
			Incidencia incidenciaToUpdate = this.incidenciaService.findIncidenciaById(incidenciaId);
			BeanUtils.copyProperties(incidencia, incidenciaToUpdate, "id","vehiculos","mecanicos", "cliente");                                                                                  
			this.incidenciaService.saveIncidencia(incidenciaToUpdate);
			List<Mecanico> mecanicosRemove = mecanicoService.findMecanicosByIncidencia(incidenciaToUpdate.getId());
			if(!mecanicos.isEmpty()) {
				for(Mecanico mecanico : mecanicosRemove) {
					incidenciaToUpdate.removeMecanicos(mecanico);
					mecanico.removeIncidencia(incidenciaToUpdate);
					mecanicoService.saveMecanico(mecanico);
				}
				for(String mecanicoDni : mecanicos.get()) {
					Mecanico mecanico = mecanicoService.findById(mecanicoDni);
					mecanico.addIncidencia(incidenciaToUpdate);
					incidenciaToUpdate.addMecanico(mecanico);
					mecanicoService.saveMecanico(mecanico);
				}
			}
			
			if(!clientes.isEmpty()) {
				for(String clienteId : clientes.get()) {
					Cliente cliente = clienteService.findClienteById(Integer.parseInt(clienteId));
					cliente.addIncidencia(incidencia);
				}
			}
			
			return "redirect:/vehiculos/{vehiculoId}";
		}
	}

}
