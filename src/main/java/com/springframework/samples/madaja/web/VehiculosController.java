package com.springframework.samples.madaja.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
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
import com.springframework.samples.madaja.service.SearchService;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.ClienteService;

import com.springframework.samples.madaja.service.ConcesionarioService;
import com.springframework.samples.madaja.service.VehiculosService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class VehiculosController {
	
	private static final String VIEWS_VEHICULOS_CREATE_OR_UPDATE_FORM = "vehiculos/createOrUpdateVehiculoForm";
	
	private final VehiculosService vehiculosService;

	private final SearchService searchService;

	private final ConcesionarioService concesionarioService;

	@Autowired
	public VehiculosController(VehiculosService vehiculosService, ConcesionarioService concesionarioService,SearchService searchService) {

		this.vehiculosService=vehiculosService;
		this.searchService = searchService;
		this.concesionarioService = concesionarioService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	//PAGINACIÓN
	@GetMapping(value = { "/vehiculos" })
    public String findAll(@RequestParam Map<String, Object> params, ModelMap model){

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1 ) : 0;

        PageRequest pageRequest = PageRequest.of(page, 10);

        Page<Vehiculos> pageVehiculos = this.vehiculosService.getAll(pageRequest);
        
        if(pageVehiculos == null) {
        	log.warn("No se han podido encontrar ningún vehículo");
        }

        int totalPage = pageVehiculos.getTotalPages();
        if(totalPage > 0) {
            // lista con todas las páginas que hay:
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        
        Collection<Disponible> disponible = this.vehiculosService.findAllDisponibles();
        model.put("disponible", disponible);
        
        model.addAttribute("vehiculos", pageVehiculos.getContent());
        model.addAttribute("current", page+1);
        model.addAttribute("next", page+2);
        model.addAttribute("prev", page);
        model.addAttribute("max", totalPage);
        
      
        return "vehiculos/mostrarVehiculos";
	}
    //
	/*
	@GetMapping(value = { "/vehiculos" })
	public String showVehiculosList(Map<String, Object> model) {
		Collection<Vehiculos> vehiculos = this.vehiculosService.findAllVehiculos();
		Collection<Disponible> disponible = this.vehiculosService.findAllDisponibles();
		model.put("vehiculos", vehiculos);
		model.put("disponible", disponible);
		return "vehiculos/mostrarVehiculos";
	}*/
	
	@GetMapping(value = "/vehiculos/new")
	public String initCreationForm(Map<String, Object> model) {
		Vehiculos vehiculo = new Vehiculos();
		model.put("vehiculos", vehiculo);
		model.put("cambios", this.vehiculosService.findAllCambios());
		model.put("concesionarios", this.concesionarioService.findAllConcesionarios());
		model.put("disponibles", this.vehiculosService.findAllDisponibles());
		model.put("combustibles", this.vehiculosService.findAllCombustibles());
		model.put("seguros", this.vehiculosService.findAllSeguros());
		return VIEWS_VEHICULOS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/vehiculos/new")
	public String processCreationForm(@Valid Vehiculos vehiculo, BindingResult result, Map<String, Object> model) {
		if (result.hasErrors()) {
			model.put("cambios", this.vehiculosService.findAllCambios());
			model.put("concesionarios", this.concesionarioService.findAllConcesionarios());
			model.put("disponibles", this.vehiculosService.findAllDisponibles());
			model.put("combustibles", this.vehiculosService.findAllCombustibles());
			model.put("seguros", this.vehiculosService.findAllSeguros());
			return VIEWS_VEHICULOS_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.vehiculosService.saveVehiculo(vehiculo);
			
			return "redirect:/vehiculos/" + vehiculo.getId();
		}
	}
	
	@GetMapping(value = "/vehiculos/{vehiculoId}/edit")
	public String initUpdateForm(@PathVariable("vehiculoId") int vehiculoId, ModelMap model) {
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		model.put("vehiculos", vehiculo);
		model.put("cambios", this.vehiculosService.findAllCambios());
		model.put("concesionarios", this.concesionarioService.findAllConcesionarios());
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
		log.info("Se realizado una consulta al vehiculo con id: " + vehiculoId);
		return mav;
	}
	
	@GetMapping(value = "/vehiculos/{vehiculoId}/delete")
	public String deleteVehiculo(@PathVariable("vehiculoId") int vehiculoId, Map<String, Object> model) {
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		Disponible disponible = this.vehiculosService.findDisponibleById(7);
		vehiculo.setDisponible(disponible);
		log.info("Este vehículo con id: " + vehiculoId + " ha pasado al estado: " + disponible.getName());
		this.vehiculosService.saveVehiculo(vehiculo);
		return "redirect:/vehiculos";
	}
	
	/*
	@GetMapping(value="/vehiculos/disponible/{disponibleId}")
	public String showVehiculosDisponibleList(@PathVariable("disponibleId") int disponibleId, 
			Map<String, Object> model) {
		Collection<Vehiculos> vehiculos = this.vehiculosService.findByDisponible(disponibleId);
		Collection<Disponible> disponible = this.vehiculosService.findAllDisponibles();
		model.put("vehiculos", vehiculos);
		model.put("disponible", disponible);
		return "vehiculos/mostrarVehiculos";
	}*/
	
	//PAGINACIÓN (disponible)
	@GetMapping(value = { "/vehiculos/disponible/{disponibleId}" })
    public String findAll(@PathVariable("disponibleId") int disponibleId, @RequestParam Map<String, Object> params, ModelMap model){

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1 ) : 0;

        PageRequest pageRequest = PageRequest.of(page, 2);

        Page<Vehiculos> pageVehiculosD = this.vehiculosService.getAllD(disponibleId, pageRequest);

        int totalPage = pageVehiculosD.getTotalPages();
        if(totalPage > 0) {
            // lista con todas las páginas que hay:
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        
        Collection<Disponible> disponible = this.vehiculosService.findAllDisponibles();
        model.put("disponible", disponible);

        model.addAttribute("vehiculos", pageVehiculosD.getContent());
        model.addAttribute("current", page+1);
        model.addAttribute("next", page+2);
        model.addAttribute("prev", page);
        model.addAttribute("max", totalPage);
        return "vehiculos/mostrarVehiculos";
	}
    //
	
	/** Reservar vehiculo  **/
	@GetMapping(value = "/reservar/{vehiculoId}")
	public String reservarVehiculo(@PathVariable("vehiculoId") int vehiculoId, ModelMap model) {		
		String view = "reservas/createReservaForm";
		
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		model.put("vehiculos", vehiculo);
				
		return view;
	}

	@GetMapping(value= {"/searchVehiculos"})
	public String initFindForm(ModelMap model) {
		model.put("vehiculo",new Vehiculos());
		return "vehiculos/mostrarVehiculos";
	}
	
	@PostMapping(value = {"/doSearchVehiculos"})
	public String searchVehiculos(@RequestParam(value="search",required = false) String searchText, ModelMap model) {
		if(searchText == "") {
			return "redirect:/vehiculos";
		}
		model.put("vehiculos",this.searchService.searchVehiculos(searchText));
		log.info("Se ha realizado la siguiente búsqueda de vehículos: " + searchText);
		return "vehiculos/mostrarVehiculos";
	}
}