package com.springframework.samples.madaja.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public List<Oferta> getOfertasList() {
		return ofertaService.findAllOfertas().stream().collect(Collectors.toList());
	}
	
	@RequestMapping(method = RequestMethod.POST, value= {"/saveOferta", "/saveOferta/{ids}"})
	public ResponseEntity<?> saveOferta(@Valid @RequestBody Oferta oferta, Errors errors, @PathVariable("ids")  Optional<List<Integer>> ids){
		if(errors.hasErrors()) {
			List<APIerror> errores = validacion(oferta);
			return ResponseEntity.badRequest().body(errores);
		}else {
			if(oferta.getId() == null) {
				ofertaService.saveOferta(oferta);
				if(!ids.isEmpty()) {
					for(int id : ids.get()) {
						Vehiculos vehiculo = vehiculosService.findVehiculoById(id);
						vehiculo.setOferta(oferta);
						vehiculosService.saveVehiculo(vehiculo);
					}
				}
				return new ResponseEntity<>(HttpStatus.CREATED);
			}else{
				if(ofertaService.findOfertaById(oferta.getId()) != null) {
					return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
				}else {
					ofertaService.saveOferta(oferta);
					if(!ids.isEmpty()) {
						for(int id : ids.get()) {
							Vehiculos vehiculo = vehiculosService.findVehiculoById(id);
							vehiculo.setOferta(oferta);
							vehiculosService.saveVehiculo(vehiculo);
						}
					}
					return new ResponseEntity<>(HttpStatus.CREATED);
				}
			}
			
		} 
	}
	@GetMapping(value = "/oferta/{id}")
	public ResponseEntity<Oferta> detailsOferta(@PathVariable int id) {
		Oferta oferta = ofertaService.findOfertaById(id);
		if(oferta == null) {
			return new ResponseEntity<>(oferta,HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(oferta,HttpStatus.OK);
		}
	}
	@RequestMapping(method = RequestMethod.PUT, value= {"/oferta/{id}", "/oferta/{id}/{ids}"})
	public ResponseEntity<?> updateOferta(@Valid @RequestBody Oferta oferta,Errors errors, @PathVariable("id") int id, @PathVariable(name = "ids", required = false)  Optional<List<Integer>> ids) {
		if(errors.hasErrors()) {
			List<APIerror> errores = validacion(oferta);
			return ResponseEntity.badRequest().body(errores);
		}else {
			if(this.ofertaService.findOfertaById(id) != null) {
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
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Oferta> deleteOferta(@PathVariable Integer id){
		Oferta oferta = this.ofertaService.findOfertaById(id);
		if(oferta != null) {
			if(!oferta.getVehiculos().isEmpty()) {
				List<Vehiculos> vehiculosOld = vehiculosService.findByOferta(id).stream().collect(Collectors.toList());
				for(Vehiculos vehiculo: vehiculosOld) {
					vehiculo.setOferta(null);
				}
			}
			this.ofertaService.deleteById(id);
			return new ResponseEntity<>(oferta,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(oferta,HttpStatus.NOT_FOUND);
		}		
	}

	public List<APIerror> validacion(Oferta oferta){
		List<APIerror> errores = new ArrayList<>();
		if(oferta.getName() == null || oferta.getName().isEmpty()) {
			APIerror error = new APIerror();
			error.setField("name");
			error.setMsg("El nombre no puede estar vacío");
			errores.add(error);
		}
		if(oferta.getDescuento() == null || oferta.getDescuento() < 0) {
			APIerror error = new APIerror();
			error.setField("descuento");
			error.setMsg("El descuento no puede estar vacío");
			errores.add(error);
		}
		if(oferta.getFechaLimite() == null || oferta.getFechaLimite().isBefore(LocalDate.now())) {
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
		return errores;
	}
}
