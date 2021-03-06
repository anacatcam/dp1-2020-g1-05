package com.springframework.samples.madaja.web;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Envio;
import com.springframework.samples.madaja.model.EstadoEnvio;
import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Recogida;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.ConcesionarioService;
import com.springframework.samples.madaja.service.EnvioService;
import com.springframework.samples.madaja.service.RecogidaService;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.service.VentaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@PreAuthorize("isAuthenticated()")
public class AlquilerController {
	
	private static final String VIEWS_ALQUILER_CREATE_FORM = "alquiler/createAlquilerForm";
	
	private static final String ERROR_VIEW="operacionImposible";

	private final AlquilerService alquilerService;
	
	private final ClienteService clienteService;
	
	private final VehiculosService vehiculosService;
	
	private final VentaService ventaService;

	private final ConcesionarioService concesionarioService;
	
	private final EnvioService envioService;
	
	private final RecogidaService recogidaService;
	
	@Autowired
	public AlquilerController(AlquilerService alquilerService,ClienteService clienteService, VentaService ventaService,
			VehiculosService vehiculosService, ConcesionarioService concesionarioService, EnvioService envioService,
			RecogidaService recogidaService) {
		this.alquilerService = alquilerService;
		this.clienteService = clienteService;
		this.vehiculosService = vehiculosService;
		this.ventaService = ventaService;
		this.concesionarioService = concesionarioService;
		this.envioService = envioService;
		this.recogidaService = recogidaService;

	}
	
	@InitBinder("alquiler")
	public void initVehiculoBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder
	public void setValidator(WebDataBinder dataBinder) {
		dataBinder.setValidator(new AlquilerValidator());
	}
	
	@GetMapping(value = {"/alquileres"})
	public String mostrarAlquileres(ModelMap modelMap) {
		String vista = ("/alquiler/mostrarAlquileres");
		Iterable<Alquiler> alquileres = alquilerService.findAllAlquiler();
		modelMap.addAttribute("alquileres", alquileres);
		
		return vista;
	}
	
	@GetMapping(value = {"/MisAlquileres"})
	public String showMisAlquileres(ModelMap model){
		List<Alquiler> alqList = new ArrayList<>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
		}else {
			 username = principal.toString();
		}
		Cliente cliente = this.clienteService.findClienteByUsername(username);
		
		alqList.addAll(this.alquilerService.findAlquilerByDni(cliente.getDni()));
		model.put("alquileres", alqList);
		
		
		return "/alquiler/mostrarMisAlquileres";
	}
	
	//-------------------------------------API--------------------------------
	@GetMapping(value = {"/alquileresAPI/{clienteId}"})
	public String showMisAlquileresListAPI(@PathVariable("clienteId") int clienteId, ModelMap map) {
		map.put("clienteId", clienteId);
		return "alquiler/mostrarMisAlquileresAPI";
	}
	//-------------------------------------API--------------------------------
	
	@GetMapping(value = "/vehiculos/{vehiculoId}/alquilar")
	public String initAlquilarVehiculo(@PathVariable("vehiculoId") int vehiculoId, Map<String, Object> model) {
		//comprobaci??n de que el vehiculo no est?? alquilado ya
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		Map<Boolean, LocalDate> alquilado = estaAlquilado(vehiculo);
		Boolean vendido  = estaVendido(vehiculo);
		if(alquilado.containsKey(true)) {
			model.put("esAlquiler", true);
			model.put("fecha", alquilado.get(true));
			log.info("El veh??culo est?? ya alquilado y no se ha podido realizar el alquiler");
			return ERROR_VIEW;
		}else if(Boolean.TRUE.equals(vendido)) {
			model.put("esVentaAlquiler", vendido);
			log.info("El veh??culo ya est?? vendido y no se ha podido realizar el alquiler");
			return ERROR_VIEW;
		}else if(estaEnRevision(vehiculo)){
			model.put("enRevision", estaEnRevision(vehiculo));
			model.put("esRevisionAlquiler", true);
			log.info("El veh??culo est?? en revisi??n y no se ha podido realizar el alquiler");
			return ERROR_VIEW;
		}else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			if(principal instanceof UserDetails) {
				 username = ((UserDetails)principal).getUsername();
			}else {
				 username = principal.toString();
			}
			
			//Obtener cliente logueado y cambiar disponibilidad vehiculo
			Cliente cliente = this.clienteService.findClienteByUsername(username);
			if(cliente.getEsConflictivo().equals("Si")) {
				model.put("esConflictivo", true);
				log.warn("El cliente es conflictivo y no se ha podido realizar el alquiler");
				return ERROR_VIEW;
			}
			//Crear alquiler
			Alquiler nuevoAlquiler = new Alquiler();
			nuevoAlquiler.setCliente(cliente);
			nuevoAlquiler.setVehiculo(vehiculo);
			nuevoAlquiler.setReserva(null);
			nuevoAlquiler.setDepLleno(true);
			nuevoAlquiler.setDevuelto(false);
			nuevoAlquiler.setRecogida(null);
			nuevoAlquiler.setEnvio(null);
			model.put("alquiler", nuevoAlquiler);
			
			//obtener concesionarios donde entregar y recoger el veh??culo
			Iterable<Concesionario> concesionarios = concesionarioService.findAllConcesionarios();
			model.put("concesionarios", concesionarios);
			
			
			return VIEWS_ALQUILER_CREATE_FORM;
		}

	}
	
	@PostMapping(value = "/vehiculos/{vehiculoId}/alquilar")
	public String processAlquilarVehiculo(@PathVariable("vehiculoId") int vehiculoId, @Valid Alquiler alquiler, 
			/**/@RequestParam(name = "concesionariosE") Concesionario concesionarioE,
			@RequestParam(name = "concesionariosR") Concesionario concesionarioR,/**/ 
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			log.warn("No se ha podido realizar el alquiler");
			return VIEWS_ALQUILER_CREATE_FORM;
		}
		else {
			//Comprobamos que no ha hecho ninguna compra hace menos de 30 d??as
			Map<Boolean, LocalDate> alquilerEnMismoPeriodo = alquilerEnMismoPeriodo(alquiler);
			if (alquilerEnMismoPeriodo.containsKey(true)) {
				model.put("alquilerEnMismoPeriodo", true);
				model.put("fechaFin", alquilerEnMismoPeriodo.get(true));
				return ERROR_VIEW;
			}
			Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
			vehiculo.setDisponible(this.vehiculosService.findDisponibleById(4));
			this.vehiculosService.saveVehiculo(vehiculo);
			alquilerService.saveAlquiler(alquiler);
			log.info("Este veh??culo con id: " + vehiculoId + " ha sido alquilado ");
			
			//////////////////////////////
			//Crear envio
			Envio nuevoEnvio = new Envio();
			nuevoEnvio.setAlquiler(alquiler);
			nuevoEnvio.setCodigoPostal(concesionarioE.getCodigoPostal());
			nuevoEnvio.setDireccion(concesionarioE.getDireccion());
			nuevoEnvio.setFecha(alquiler.getFechaInicio());
			nuevoEnvio.setHora(LocalTime.of(9, 00, 00)); //por defecto
			EstadoEnvio estadoEnvio = new EstadoEnvio();
			estadoEnvio.setId(1); //por defecto, se crea con estado "1 = pendiente"
			nuevoEnvio.setEstadoEnvio(estadoEnvio);
			nuevoEnvio.setLocalidad(concesionarioE.getLocalidad());
			nuevoEnvio.setPais(concesionarioE.getPais());
			nuevoEnvio.setProvincia(concesionarioE.getProvincia());
			alquiler.setEnvio(nuevoEnvio);
			envioService.saveEnvio(nuevoEnvio);
			
			//Crear recogida
			Recogida nuevaRecogida = new Recogida();
			nuevaRecogida.setAlquiler(alquiler);//
			nuevaRecogida.setCodigoPostal(concesionarioR.getCodigoPostal());
			nuevaRecogida.setDireccion(concesionarioR.getDireccion());
			nuevaRecogida.setFecha(alquiler.getFechaFin());
			nuevaRecogida.setHora(LocalTime.of(9, 00, 00)); //por defecto
			nuevaRecogida.setLocalidad(concesionarioR.getLocalidad());
			nuevaRecogida.setPais(concesionarioR.getPais());
			nuevaRecogida.setProvincia(concesionarioR.getProvincia());
			alquiler.setRecogida(nuevaRecogida);
			recogidaService.saveRecogida(nuevaRecogida);
			//////////////////////////////

			return "redirect:/MisAlquileres";
		}
	}
	
	@GetMapping(value = "/alquileres/{alquilerId}/devolucion")
	public String initDevolverVehiculo(@PathVariable("alquilerId") int alquilerId, Map<String, Object> model) {
		model.put("alquiler_id", alquilerId);
		model.put("disponibles", this.vehiculosService.findAllDisponibles());
		return "vehiculos/devolverVehiculo";
	}
	
	@PostMapping(value = "/alquileres/{alquilerId}/devolucion")
	public String processDevolverVehiculo(ModelMap model, @RequestParam(name="AlquilerId") Integer alquilerId,
				@RequestParam(name="disponible") Integer disponible, 
				@RequestParam(name="FechaDevolucion") String fechaDevolucion) {
		if (alquilerId==null || fechaDevolucion.equals("") || disponible==null) {
			model.put("alquiler_id", alquilerId);
			model.put("disponibles", this.vehiculosService.findAllDisponibles());
			return "vehiculos/devolverVehiculo";
		}
		else {
			Alquiler alquiler = this.alquilerService.findAlquilerById(alquilerId);
			alquiler.setDevuelto(true);
			Integer vehiculoId = alquiler.getVehiculo().getId();
			Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
			Disponible disponibilidad = this.vehiculosService.findDisponibleById(disponible);
			vehiculo.setDisponible(disponibilidad);
			
			//Comprobamos si hay retraso en la fecha de devoluci??n
			String devolucion = fechaDevolucion;
			LocalDate fechaFin = alquiler.getFechaFin();
			Integer retraso = esRetraso(devolucion, fechaFin);
			alquiler.getCliente().setDiasRetraso(alquiler.getCliente().getDiasRetraso() + retraso);
			if (alquiler.getCliente().getDiasRetraso() > 14) {
				alquiler.getCliente().setEsConflictivo("Si");
			}
			this.alquilerService.saveAlquiler(alquiler);
			this.vehiculosService.saveVehiculo(vehiculo);
			
		}
		
		return "redirect:/vehiculos";
		
	}
	
	public Map<Boolean, LocalDate> estaAlquilado(Vehiculos vehiculo) {
		Map<Boolean, LocalDate> res = new HashMap<>();
		Iterable<Alquiler> alquileres = this.alquilerService.findAllAlquiler();
		for(Alquiler a:alquileres) {
			if(vehiculo.equals(a.getVehiculo()) && a.getFechaFin().isAfter(LocalDate.now()) && a.getDevuelto()==Boolean.FALSE) {
				res.put(true, a.getFechaFin());
				return res;
			}
		}
		res.put(false, null);
		return res;
	}
	
	public boolean estaEnRevision(Vehiculos vehiculo) {
		Boolean res = false;
		List<Incidencia> incidencias = vehiculo.getIncidencias();
		for(Incidencia i:incidencias) {
			if(Boolean.FALSE.equals(i.getSolucionada())) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	public Integer esRetraso(String fechaDevolucion, LocalDate fechaFin) {
		LocalDate devolucion = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(fechaDevolucion));
		long retraso = DAYS.between(fechaFin, devolucion);
		return (int)retraso;
	}
	
	public Map<Boolean, LocalDate> alquilerEnMismoPeriodo(Alquiler alquiler) {
		Map<Boolean, LocalDate> res = new HashMap<>();
		List<Alquiler> alquileres = alquiler.getCliente().getAlquileres();
		if (alquileres.get(0).getFechaFin().isAfter(alquiler.getFechaInicio())) {
			res.put(true, alquileres.get(0).getFechaFin());
			return res;
		}
		res.put(false, null);
		return res;
		
	}
	
	public boolean estaVendido(Vehiculos vehiculo) {
		Boolean res = false;
		Iterable<Venta> ventas = this.ventaService.findAllVentas();
		for(Venta v:ventas) {
			if(vehiculo.equals(v.getVehiculo())) {
				res = true;
				return res;
			}
		}
		return res;
	}
	
}
