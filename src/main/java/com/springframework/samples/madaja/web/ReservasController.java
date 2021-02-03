package com.springframework.samples.madaja.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Reserva;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.ReservaService;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.service.VentaService;

@Controller
@RequestMapping("/reservas")
public class ReservasController {
	
	private static final String VIEWS_RESERVA_CREATE_FORM = "reservas/crearReservaForm";
	
	private final ReservaService reservaService;

	private final VentaService ventaService;
	
	private final AlquilerService alquilerService;

	private final ClienteService clienteService;
	
	private final VehiculosService vehiculosService;
	
	@Autowired
	public ReservasController(ReservaService reservaService, VentaService ventaService, AlquilerService alquilerService,
			ClienteService clienteService, VehiculosService vehiculosService) {
		this.reservaService = reservaService;
		this.ventaService = ventaService;
		this.alquilerService = alquilerService;
		this.clienteService = clienteService;
		this.vehiculosService = vehiculosService;
	}

	@InitBinder("reserva")
	public void initReservaBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(path="/new")
	public String crearReserva(ModelMap modelMap) {
		Reserva reserva = new Reserva();
		modelMap.put("reserva", reserva);		
		return "reservas/mostrarReservas";
	}
	
	@PostMapping(path="/new")
	public String processCrearReserva(@Valid Reserva reserva, BindingResult result, ModelMap modelMap) {
		if(result.hasErrors()) {
			modelMap.put("reserva", reserva);
			return "reservas/editReservaForm";
		} else {
			this.reservaService.save(reserva);
			modelMap.addAttribute("message", reserva);
			return "reservas/mostrarReservas";
		}
		
	}
	
	/** Todas las reservas (vista del administrador) **/
	@GetMapping()
	public String mostrarReservas(ModelMap modelMap) {
		String vista = ("/reservas/mostrarReservas");
		Iterable<Venta> ventas = ventaService.findAllVentas();
		modelMap.addAttribute("ventas", ventas);
		Iterable<Alquiler> alquileres = alquilerService.findAllAlquiler();
		modelMap.addAttribute("alquileres", alquileres);
		
		return vista;
	}
	
	/** Mis reservas (reservas del usuario autenticado) **/
	@GetMapping(value = {"/mis-reservas"})
	public String mostrarMisReservas(ModelMap modelMap) {
		String vista = ("/reservas/mostrarMisReservas");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
		}else {
			 username = principal.toString();
		}
		Cliente cliente = this.clienteService.findClienteByUsername(username);
		
		List<Alquiler> alqList = new ArrayList<>();
		List<Venta> venList = new ArrayList<>();
		alqList.addAll(this.alquilerService.findAlquilerByDni(cliente.getDni()));
		venList.addAll(this.ventaService.findVentasByDni(cliente.getDni()));
		modelMap.put("alquileres", alqList);
		modelMap.put("ventas", venList);
		
		return vista;
	}
	
	

	@GetMapping(path = "/{reservaId}/delete")
	public String borrarReservas(@PathVariable("reservaId") int reservaId, ModelMap modelMap) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String autoridad;
		String username = "";
		String view = "";
		List<Reserva> reservas = new ArrayList<>();
		
		if(principal instanceof UserDetails) {
			 autoridad = ((UserDetails)principal).getAuthorities().iterator().next().toString();
			 username = ((UserDetails)principal).getUsername();
		}else {
			 autoridad = principal.toString();
		}
		
		if(autoridad.equals("admin")) {
			Optional<Reserva> reserva = reservaService.findReservaById(reservaId);
			if (reserva.isPresent()) {
				reservaService.deleteRes(reservaId);
				view = "reservas/mostrarReservas";
				mostrarReservas(modelMap);
				modelMap.addAttribute("message", "Reserva anulada correctamente");
			} else {
				modelMap.addAttribute("message", "Reserva no encontrada");
			}
		} else if (autoridad.equals("cliente")) {
			Cliente cliente = this.clienteService.findClienteByUsername(username);
			String dni = cliente.getDni();
			reservas = this.reservaService.findByDNI(dni);
			for (Reserva r : reservas) {
				if(r.getId().equals(reservaId)) {
					this.reservaService.deleteRes(reservaId);
					view = "reservas/mostrarMisReservas";
					mostrarMisReservas(modelMap);
					modelMap.addAttribute("message", "Reserva anulada correctamente");
					break;
				}else {
					return "redirect:/oups";
				}
			}
		}
		return view;
	}
	
	@GetMapping(value = "/nuevaReserva/{vehiculoId}")
	public String nuevaReserva(@PathVariable("vehiculoId") int vehiculoId, Map<String, Object> model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
		}else {
			 username = principal.toString();
		}
		
		Cliente cliente = this.clienteService.findClienteByUsername(username);
		if(cliente.getEsConflictivo().equals("Si")) {
			model.put("esConflictivo", true);
			return "operacionImposible";
		}
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		model.put("cliente", cliente);
		model.put("vehiculo", vehiculo);
		
		return "reservas/seleccionarReserva";
	}
	
	@GetMapping(value = "/{vehiculoId}/reservar/{tipo}")
	public String reservarVehiculo(@PathVariable("vehiculoId") int vehiculoId, @PathVariable("tipo") String tipo,
			Map<String, Object> model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
		}else {
			 username = principal.toString();
		}
		
		//Obtener cliente logueado y vehiculo
		Cliente cliente = this.clienteService.findClienteByUsername(username);
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		
		//Crear venta
		Reserva nuevaReserva = new Reserva();
		Double fianza;
		
		if(tipo.equals("Alquiler")) { 
			fianza = vehiculo.getPrecioAlquiler().doubleValue(); //En el caso de un alquiler, la finaza es un mes del propio alquiler
		}else {
			fianza = 0.2*vehiculo.getPrecioAlquiler(); //En el caso de una venta, la finaza es un 20% del precio
		}
		
		nuevaReserva.setFianza(fianza);
		nuevaReserva.setCliente(cliente);
		model.put("reserva", nuevaReserva);
		model.put("cliente", cliente);
		
		return VIEWS_RESERVA_CREATE_FORM;
	}
	
	@PostMapping(value = "/{vehiculoId}/reservar/{tipo}")
	public String processReservarVehiculo(@PathVariable("vehiculoId") int vehiculoId, @PathVariable("tipo") String tipo,
			@Valid Reserva reserva, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return VIEWS_RESERVA_CREATE_FORM;
		}else {
			
			Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
			vehiculo.setDisponible(this.vehiculosService.findDisponibleById(6));
			this.vehiculosService.saveVehiculo(vehiculo);
			reservaService.saveReserva(reserva);
		
			return "redirect:/reservas";
		}
		
	}
		
}