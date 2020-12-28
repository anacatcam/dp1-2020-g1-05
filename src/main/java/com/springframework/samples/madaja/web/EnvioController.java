package com.springframework.samples.madaja.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Envio;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.EnvioService;

@Controller
public class EnvioController {

	private final EnvioService envioService;
	private final AlquilerService alquilerService;
	
	@Autowired
	public EnvioController(EnvioService envioService, AlquilerService alquilerService) {
		this.envioService = envioService;
		this.alquilerService = alquilerService;
	}
	
	@GetMapping("/concesionario/{concesionarioId}/Envios")
	public String showEnvios(@PathVariable("concesionarioId") int concesionarioId, ModelMap model) {
		List<Envio> envios = this.envioService.findEnvioByConcesionario(concesionarioId);

		List<Alquiler> alquileres = new ArrayList<>();
		for(Envio e:envios) {
			Alquiler a = this.alquilerService.findAlquilerByEnvio(e.getId());
			alquileres.add(a);
		}
		model.put("alquileres", alquileres);
		return "Envio/mostrarEnvios";
	}
}
