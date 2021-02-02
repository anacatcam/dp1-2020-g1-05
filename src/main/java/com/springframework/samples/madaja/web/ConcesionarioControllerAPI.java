package com.springframework.samples.madaja.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.samples.madaja.service.ConcesionarioService;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Gestor;
import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Vehiculos;

@RestController
@RequestMapping(value = "/api/v1/concesionarios/")
public class ConcesionarioControllerAPI {
	
	private final ConcesionarioService concesionarioService;
	private final VehiculosService vehiculosService;
	
	@Autowired
	public ConcesionarioControllerAPI(ConcesionarioService concesionarioService, VehiculosService vehiculosService) {
		this.concesionarioService=concesionarioService;
		this.vehiculosService=vehiculosService;
	}
	@GetMapping(value="/all")
	public List<Concesionario> showConcesionariosList(){
		return concesionarioService.findAllConcesionarios().stream().collect(Collectors.toList());
	}
	
	@GetMapping(value="/concesionario/{concesionarioId}")
	public ResponseEntity<Concesionario> concesionarioDetails(@PathVariable int concesionarioId){
		Concesionario concesionario=concesionarioService.findConcesionarioById(concesionarioId);
		ResponseEntity<Concesionario> res;
		if(concesionario == null) {
			res=new ResponseEntity<Concesionario>(concesionario,HttpStatus.NOT_FOUND);
		}else {
			res=new ResponseEntity<Concesionario>(concesionario,HttpStatus.OK);		
		}
		return res;
	}
	
	@GetMapping(value="/concesionario/{concesionarioId}/gestores")
	public ResponseEntity<List<Gestor>> gestoresDetails(@PathVariable int concesionarioId){
		List<Gestor> gestores=concesionarioService.findConcesionarioById(concesionarioId).getGestores();
		ResponseEntity<List<Gestor>> res;
		if(gestores == null) {
			res=new ResponseEntity<List<Gestor>>(gestores,HttpStatus.NOT_FOUND);
		}else {
			res=new ResponseEntity<List<Gestor>>(gestores,HttpStatus.OK);		
		}
		return res;
	}
	
	@GetMapping(value="/concesionario/{concesionarioId}/vehiculos")
	public ResponseEntity<List<Vehiculos>> vehiculosConcesionario(@PathVariable int concesionarioId){
		List<Vehiculos> vehiculos=concesionarioService.findConcesionarioById(concesionarioId).getVehiculos();
		ResponseEntity<List<Vehiculos>> res;
		if(vehiculos == null) {
			res=new ResponseEntity<List<Vehiculos>>(vehiculos,HttpStatus.NOT_FOUND);
		}else {
			res=new ResponseEntity<List<Vehiculos>>(vehiculos,HttpStatus.OK);		
		}
		return res;
	}
	
	@GetMapping(value="/concesionario/{concesionarioId}/vehiculos/{vehiculoId}/disponibilidad")
	public ResponseEntity<Disponible> disponibilidadVehiculosConcesionario(@PathVariable int concesionarioId, @PathVariable int vehiculoId){
		Disponible disponibilidad=vehiculosService.findVehiculoById(vehiculoId).getDisponible();
		ResponseEntity<Disponible> res;
		if(disponibilidad == null) {
			res=new ResponseEntity<Disponible>(disponibilidad,HttpStatus.NOT_FOUND);
		}else {
			res=new ResponseEntity<Disponible>(disponibilidad,HttpStatus.OK);		
		}
		return res;
	}
	
	@GetMapping(value="/concesionario/{concesionarioId}/vehiculos/{vehiculoId}/incidencias")
	public ResponseEntity<List<Incidencia>> incidenciasVehiculosConcesionario(@PathVariable int concesionarioId, @PathVariable int vehiculoId){
		List<Incidencia> incidencias=new ArrayList<>();
		
		//REVISAR
		List<Vehiculos> vehiculos=concesionarioService.findConcesionarioById(concesionarioId).getVehiculos();
		for(int i=0; i<vehiculos.size(); i++) {
			for(int j=0; j<vehiculos.get(i).getIncidencias().size(); j++) {
				incidencias.add(vehiculos.get(i).getIncidencias().get(j));
			}
		}
		ResponseEntity<List<Incidencia>> res;
		if(incidencias == null) {
			res=new ResponseEntity<List<Incidencia>>(incidencias,HttpStatus.NOT_FOUND);
		}else {
			res=new ResponseEntity<List<Incidencia>>(incidencias,HttpStatus.OK);		
		}
		return res;
	}
	
}

