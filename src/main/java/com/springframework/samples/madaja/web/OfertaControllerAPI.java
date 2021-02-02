package com.springframework.samples.madaja.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.aspectj.weaver.AjAttribute;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.OfertaService;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.util.APIerror;

@RestController
@RequestMapping(value = "/api/v1/ofertas/")
public class OfertaControllerAPI {
	
	private OfertaService ofertaService;
	private VehiculosService vehiculosService;
	
	@Autowired
	public OfertaControllerAPI(OfertaService ofertaService, VehiculosService vehiculosService) {
		this.ofertaService=ofertaService;
		this.vehiculosService =vehiculosService;
	}
	
	@GetMapping(value = "/all")
	public List<Oferta> showOfertasList() {
		return ofertaService.findAllOfertas().stream().collect(Collectors.toList());
	}
	
	@RequestMapping(method = RequestMethod.POST, value= {"/saveOferta", "/saveOferta/{ids}"})
	public ResponseEntity<?> saveOferta(@Valid @RequestBody Oferta oferta, Errors errors, @PathVariable("ids")  Optional<List<Integer>> ids){
		List<APIerror> errores = new ArrayList<APIerror>();
		if(errors.hasErrors()) {
			if(oferta.getName() == null || oferta.getName().isEmpty()) {
				APIerror error = new APIerror();
				error.setField("name");
				error.setMsg("El nombre no puede estar vacío");
				errores.add(error);
			}
			if(oferta.getDescuento() == null) {
				APIerror error = new APIerror();
				error.setField("descuento");
				error.setMsg("El descuento no puede estar vacío");
				errores.add(error);
			}
			if(oferta.getFechaLimite() == null) {
				APIerror error = new APIerror();
				error.setField("fechaLimite");
				error.setMsg("La fecha límite no puede estar vacía");
				errores.add(error);
			}
			if(oferta.getHoraLimite() == null) {
				APIerror error = new APIerror();
				error.setField("horaLimite");
				error.setMsg("La hora límite no puede estar vacía");
				errores.add(error);
			}
			return ResponseEntity.badRequest().body(errores);
		}else {
			ofertaService.saveOferta(oferta);
			if(!ids.isEmpty()) {
				for(int id : ids.get()) {
					Vehiculos vehiculo = vehiculosService.findVehiculoById(id);
					vehiculo.setOferta(oferta);
					vehiculosService.saveVehiculo(vehiculo);
				}
			}
			return new ResponseEntity<Oferta>(HttpStatus.OK);
		} 
	}
	@GetMapping(value = "/oferta/{id}")
	public ResponseEntity<Oferta> detailsOferta(@PathVariable int id) {
		Oferta oferta = ofertaService.findOfertaById(id);
		if(oferta == null) {
			return new ResponseEntity<Oferta>(oferta,HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Oferta>(oferta,HttpStatus.OK);
		}
	}
	@RequestMapping(method = RequestMethod.PUT, value= {"/oferta/{id}", "/oferta/{id}/{ids}"})
	public ResponseEntity<?> updateOferta(@Valid @RequestBody Oferta oferta,Errors errors, @PathVariable("id") int id, @PathVariable(name = "ids", required = false)  Optional<List<Integer>> ids) {
		List<APIerror> errores = new ArrayList<APIerror>();
		System.out.println("AQUI");
		System.out.println(oferta.getDescuento());
		if(errors.hasErrors()) {
			if(oferta.getName() == null || oferta.getName().isEmpty()) {
				APIerror error = new APIerror();
				error.setField("name");
				error.setMsg("El nombre no puede estar vacío");
				errores.add(error);
			}
			if(oferta.getDescuento() == null) {
				APIerror error = new APIerror();
				error.setField("descuento");
				error.setMsg("El descuento no puede estar vacío");
				errores.add(error);
			}
			if(oferta.getFechaLimite() == null) {
				APIerror error = new APIerror();
				error.setField("fechaLimite");
				error.setMsg("La fecha límite no puede estar vacía");
				errores.add(error);
			}
			if(oferta.getHoraLimite() == null) {
				APIerror error = new APIerror();
				error.setField("horaLimite");
				error.setMsg("La hora límite no puede estar vacía");
				errores.add(error);
			}
			return ResponseEntity.badRequest().body(errores);
		}else {
			if(!ids.isEmpty()) {
				List<Vehiculos> vehiculosOld = vehiculosService.findByOferta(id).stream().collect(Collectors.toList());
				for(Vehiculos vehiculo: vehiculosOld) {
					vehiculo.setOferta(null);
				}
				Oferta ofertaUpdate = this.ofertaService.findOfertaById(id);
				BeanUtils.copyProperties(oferta, ofertaUpdate,"id");
				this.ofertaService.saveOferta(ofertaUpdate);
				for(int id2 : ids.get()) {
					Vehiculos vehiculo = vehiculosService.findVehiculoById(id2);
					vehiculo.setOferta(ofertaUpdate);
					vehiculosService.saveVehiculo(vehiculo);
				}
			}else {
				Oferta ofertaUpdate = this.ofertaService.findOfertaById(id);
				BeanUtils.copyProperties(oferta, ofertaUpdate,"id");
				this.ofertaService.saveOferta(ofertaUpdate);
			}
			return new ResponseEntity<Oferta>(HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/delete/{id}")
	public ResponseEntity<Oferta> delete(@PathVariable Integer id){
		Oferta oferta = this.ofertaService.findOfertaById(id);
		if(oferta != null) {
			if(!oferta.getVehiculos().isEmpty()) {
				List<Vehiculos> vehiculosOld = vehiculosService.findByOferta(id).stream().collect(Collectors.toList());
				for(Vehiculos vehiculo: vehiculosOld) {
					vehiculo.setOferta(null);
				}
			}
			this.ofertaService.deleteById(id);
			return new ResponseEntity<Oferta>(oferta,HttpStatus.OK);
		}else {
			return new ResponseEntity<Oferta>(oferta,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	
}
