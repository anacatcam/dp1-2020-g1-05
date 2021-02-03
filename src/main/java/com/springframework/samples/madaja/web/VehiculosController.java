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
import com.springframework.samples.madaja.service.ConcesionarioService;
import com.springframework.samples.madaja.service.VehiculosService;

@Controller
public class VehiculosController {
	
	private static final String VIEWS_VEHICULOS_CREATE_OR_UPDATE_FORM = "vehiculos/createOrUpdateVehiculoForm";
	
	private final VehiculosService vehiculosService;
	
	private final ConcesionarioService concesionarioService;
	
	@Autowired
	public VehiculosController(VehiculosService vehiculosService, ConcesionarioService concesionarioService) {
		this.vehiculosService=vehiculosService;
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

        PageRequest pageRequest = PageRequest.of(page, 2);

        Page<Vehiculos> pageVehiculos = this.vehiculosService.getAllPagVehiculos(pageRequest);
        Page<Disponible> pageDisponible = this.vehiculosService.getAllPagDisponible(pageRequest);

        int totalPage = pageVehiculos.getTotalPages()+pageDisponible.getTotalPages();
        if(totalPage > 0) {
            // lista con todas las páginas que hay:
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        model.addAttribute("vehiculos", pageVehiculos.getContent());
        model.addAttribute("disponible", pageDisponible.getContent());
        model.addAttribute("current", page+1);
        model.addAttribute("next", page+2);
        model.addAttribute("prev", page);
        model.addAttribute("max", totalPage);
        return "vehiculos/mostrarVehiculos";
	}
   /* OBSOLETO POR PAGINACIÓN
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
		return mav;
	}
	
	@GetMapping(value = "/vehiculos/{vehiculoId}/delete")
	public String deleteVehiculo(@PathVariable("vehiculoId") int vehiculoId, Map<String, Object> model) {
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		Disponible disponible = this.vehiculosService.findDisponibleById(7);
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
		model.put("vehiculos", this.vehiculosService.searchVehiculos(searchText));
		
		return "vehiculos/mostrarVehiculos";
	}
}