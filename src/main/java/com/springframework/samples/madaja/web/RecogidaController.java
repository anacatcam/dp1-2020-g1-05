package com.springframework.samples.madaja.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Envio;
import com.springframework.samples.madaja.model.Mecanico;
import com.springframework.samples.madaja.model.Recogida;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.EnvioService;
import com.springframework.samples.madaja.service.MecanicoService;
import com.springframework.samples.madaja.service.RecogidaService;
import com.springframework.samples.madaja.service.VentaService;

@Controller
public class RecogidaController {

	private final RecogidaService recogidaService;
	
	private final MecanicoService mecanicoService;
	
	@Autowired
	public RecogidaController(RecogidaService recogidaService, MecanicoService mecanicoService) {
		this.recogidaService = recogidaService;
		this.mecanicoService = mecanicoService;
	}
	
	@InitBinder("recogida")
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
		dataBinder.setDisallowedFields("vehiculo");
		dataBinder.setDisallowedFields("matricula");
		dataBinder.setDisallowedFields("hora");
		dataBinder.setDisallowedFields("cliente");
		dataBinder.setDisallowedFields("direccion");
	}
	
	@GetMapping("/recogidas")
	public String mostrarRecogidas(ModelMap modelMap) {
		Iterable<Recogida> recogidas = recogidaService.findAllRecogidas();
		modelMap.addAttribute("recogidas", recogidas);
		
		return "recogida/mostrarRecogidas";
	}
	
	@GetMapping("/recogida/{recogidaId}/edit") 
	public String initUpdateForm(@PathVariable("recogidaId") int recogidaId, ModelMap model){
		Recogida recogida = this.recogidaService.findRecogidaById(recogidaId);
		List<Mecanico> mecanicos = this.mecanicoService.findAll();
		model.put("recogida", recogida);
		model.put("mecanicos", mecanicos);
		return "recogida/editarRecogida";
	}
	
	@PostMapping("/recogida/{recogidaId}/edit")
	public String procsessUpdateFormRecogida(@PathVariable("recogidaId") int recogidaId,
			Recogida recogida, 
			BindingResult result) {
		Recogida recogidaUpdate = this.recogidaService.findRecogidaById(recogidaId);
		//recogidaUpdate.setMecanico(mecanico);

		this.recogidaService.saveRecogida(recogidaUpdate);
		
		return "redirect:/recogidas";
	}
	
	
	
}
