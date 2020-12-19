package com.springframework.samples.madaja.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.service.SeguroVehiculoService;

@Controller
@RequestMapping("/vehiculos/{vehiculoId}")
public class SeguroVehiculoController {
	
	private final SeguroVehiculoService seguroVehiculoService;

	
	@Autowired
	public SeguroVehiculoController(SeguroVehiculoService seguroVehiculoService) {
		this.seguroVehiculoService=seguroVehiculoService;
	}
	
	@GetMapping(value = "/seguro/{seguroVehiculoId}/view")
	public ModelAndView showSeguroVehiculo(@PathVariable("seguroVehiculoId") int seguroVehiculoId) {
		SeguroVehiculo seguroVehiculo = this.seguroVehiculoService.findSeguroVehiculoById(seguroVehiculoId);
		ModelAndView mav = new ModelAndView("seguro/seguroDetails");
		mav.addObject(seguroVehiculo);
		return mav;
	}
}
