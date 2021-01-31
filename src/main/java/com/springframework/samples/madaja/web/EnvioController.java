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

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Envio;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.EnvioService;
import com.springframework.samples.madaja.service.VentaService;

@Controller
public class EnvioController {

	private final EnvioService envioService;
	private final AlquilerService alquilerService;
	private final VentaService ventaService;
	
	@Autowired
	public EnvioController(EnvioService envioService, AlquilerService alquilerService, VentaService ventaService) {
		this.envioService = envioService;
		this.alquilerService = alquilerService;
		this.ventaService = ventaService;
	}
	
	@InitBinder("envio")
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
		dataBinder.setDisallowedFields("vehiculo");
		dataBinder.setDisallowedFields("matricula");
		dataBinder.setDisallowedFields("hora");
		dataBinder.setDisallowedFields("cliente");
		dataBinder.setDisallowedFields("direccion");
	}
	
	@GetMapping("/concesionario/{concesionarioId}/EnviosAlquileres")
	public String showEnviosAlq(@PathVariable("concesionarioId") int concesionarioId, ModelMap model) {
		List<Envio> envios = this.envioService.findEnvioAlqByConcesionario(concesionarioId);
		List<Alquiler> alquileres = new ArrayList<>();

		for(Envio e:envios) {
			Alquiler a = this.alquilerService.findAlquilerByEnvio(e.getId());
			alquileres.add(a);
		}
		model.put("objects", alquileres);
		model.put("concesionario", concesionarioId);
		return "Envio/mostrarEnvios";
	}
	
	@GetMapping("/concesionario/{concesionarioId}/EnviosVentas")
	public String showEnviosVent(@PathVariable("concesionarioId") int concesionarioId, ModelMap model) {
		List<Envio> envios = this.envioService.findEnvioVentByConcesionario(concesionarioId);
		List<Venta> ventas = new ArrayList<>();
		
		for(Envio e:envios) {
			Venta v = this.ventaService.findVentaByEnvio(e.getId());
			ventas.add(v);
		}
		model.put("objects", ventas);
		model.put("concesionario", concesionarioId);
		return "Envio/mostrarEnvios";
	}
	
	@GetMapping(value = "/concesionario/{concesionarioId}/envio={envioId}/edit")
	public String initUpdateForm(@PathVariable("envioId") int id,ModelMap model,@PathVariable("concesionarioId") int concesionarioId){
		Alquiler alq = this.alquilerService.findAlquilerByEnvio(id);// tengo que comprobar si envio es de alquiler o venta
		if(alq !=null) {
			model.put("object", alq);
		}else {
			Venta venta = this.ventaService.findVentaByEnvio(id);
			model.put("object",venta);
		}
		
		Envio envio = this.envioService.findEnvioById(id);
		model.put("estados", this.envioService.findAllEstados());
		model.put("envio", envio);

		return "/Envio/editarEnvio";
	}
	
	@PostMapping(value = "/concesionario/{concesionarioId}/envio={envioId}/edit")
	public String processUpdateFormEnvio(@PathVariable("envioId") int id, ModelMap model, @Valid Envio envio, BindingResult result,@PathVariable("concesionarioId") int concesionarioId) {
		Alquiler alq = this.alquilerService.findAlquilerByEnvio(id);
		if (result.hasErrors()) {
			if(alq !=null) {
				model.put("object", alq);
			}else {
				Venta venta = this.ventaService.findVentaByEnvio(id);
				model.put("object",venta);
			}
			model.put("envio", envio);
			return "Envio/mostrarEnvios";
		}
		else {
			Envio envioUpdate = this.envioService.findEnvioById(id);
			envioUpdate.setEstadoEnvio(envio.getEstadoEnvio());

			this.envioService.saveEnvio(envioUpdate);
			if(alq != null) {
				return "redirect:/concesionario/{concesionarioId}/EnviosAlquileres";
			}else {
				return "redirect:/concesionario/{concesionarioId}/EnviosVentas";
			}
		}
	}
}
