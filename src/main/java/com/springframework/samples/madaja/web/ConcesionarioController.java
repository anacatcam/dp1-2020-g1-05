package com.springframework.samples.madaja.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestParam;


import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.service.ConcesionarioService;

@Controller
public class ConcesionarioController {
	
	private final ConcesionarioService concesionarioService;
	
	@Autowired
	public ConcesionarioController(ConcesionarioService concesionarioService) {
		this.concesionarioService=concesionarioService;
	}
	
	/** anterior a la paginacion **/
//	@GetMapping(value = { "/concesionario" })
//	public String showConcesionariosList(Map<String, Object> model) {
//		Collection<Concesionario> concesionarios = this.concesionarioService.findAllConcesionarios();
//		model.put("concesionarios", concesionarios);		
//		return "concesionario/mostrarConcesionarios";
//	}
	
	//////
	@GetMapping(value = { "/concesionario" })
	public String findAll(@RequestParam Map<String, Object> params, ModelMap model){
		
		//equivalente a un if/else, si se cumple, hace lo de la izq de los ":" y si no, lo de la derecha:
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1 ) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 2);
		
		Page<Concesionario> pageConcesionario = this.concesionarioService.getAll(pageRequest);
		
		int totalPage = pageConcesionario.getTotalPages();
		if(totalPage > 0) {
			// lista con todas las páginas que hay:
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}

		model.addAttribute("concesionarios", pageConcesionario.getContent());
		model.addAttribute("current", page+1);
		model.addAttribute("next", page+2);
		model.addAttribute("prev", page);
		model.addAttribute("max", totalPage);
				
		return "concesionario/mostrarConcesionarios";
	}
	//////
	
	@GetMapping("/concesionario/{concesionarioId}")
	public String showConcesionario(@PathVariable("concesionarioId") int concesionarioId, ModelMap model) {
		Concesionario concesionario = this.concesionarioService.findConcesionarioById(concesionarioId);
		model.put("concesionario", concesionario);
		return "concesionario/concesionarioDetails";
	}

	@GetMapping(value= {"/searchConcesionarios"})
	public String initFindForm(ModelMap model) {
		model.put("concesionario", new Concesionario());
		return "concesionario/mostrarConcesionarios";
	}
	
	@PostMapping(value = {"/doSearchConcesionarios"})
	public String searchConcesionarios(@RequestParam(value="search",required = false) String searchText, ModelMap model) {
		
		if(searchText == "") {
			return "redirect:/concesionario";
		}
		model.put("concesionarios", concesionarioService.searchConcesionarios(searchText));
		
		return "concesionario/mostrarConcesionarios";
	}
	
	//-------------------------------------API--------------------------------
	@GetMapping(value = {"/concesionariosAPI"})
	public String showConcesionariosListAPI() {
		return "concesionario/mostrarConcesionariosAPI";
	}
	//-------------------------------------API--------------------------------
	
	//-------------------------------------API--------------------------------
	@GetMapping(value = {"/concesionariosAPI/{concesionarioId}"})
	public String showConcesionarioAPI(@PathVariable("concesionarioId") int concesionarioId, ModelMap map) {
		map.put("id", concesionarioId);
		return "concesionario/concesionarioDetailsAPI";
	}
	//-------------------------------------API--------------------------------
}
