package com.springframework.samples.madaja.web;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.service.VentaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class VentaController {
	
	private static final String ERROR_VIEW="operacionImposible";
	
	private final VentaService ventaService;
	
	private final ClienteService clienteService;
	
	private final VehiculosService vehiculosService;
	
	private final AlquilerService alquilerService;
	
	
	@Autowired
	public VentaController(VentaService ventaService, ClienteService clienteService, VehiculosService vehiculosService,
			AlquilerService alquilerService) {
		this.ventaService = ventaService;
		this.clienteService = clienteService;
		this.vehiculosService = vehiculosService;
		this.alquilerService = alquilerService;
	}
	
	//PAGINACIÓN
	@GetMapping(value = { "/MisVentas" })
    public String findAll(@RequestParam Map<String, Object> params, ModelMap model){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if(principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
		}else {
			 username = principal.toString();
		}
		Cliente cliente = this.clienteService.findClienteByUsername(username);

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1 ) : 0;

        PageRequest pageRequest = PageRequest.of(page, 12);

        Page<Venta> pageVenta = this.ventaService.getAll(cliente.getDni(), pageRequest);

        int totalPage = pageVenta.getTotalPages();
        if(totalPage > 0) {
            // lista con todas las páginas que hay:
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        model.addAttribute("ventas", pageVenta.getContent());
        model.addAttribute("current", page+1);
        model.addAttribute("next", page+2);
        model.addAttribute("prev", page);
        model.addAttribute("max", totalPage);

        return "/venta/mostrarMisVentas";
    }
	//
	
	@GetMapping(value = {"/ventas"})
	public String mostrarVentas(ModelMap modelMap) {
		String vista = ("/venta/mostrarVentas");
		Iterable<Venta> ventas = ventaService.findAllVentas();
		modelMap.addAttribute("ventas", ventas);
		
		return vista;
	}

	
	@GetMapping(value = "/vehiculos/{vehiculoId}/comprar")
	public String comprarVehiculo(@PathVariable("vehiculoId") int vehiculoId, Map<String, Object> model) {
		Vehiculos vehiculo = this.vehiculosService.findVehiculoById(vehiculoId);
		Map<Boolean, LocalDate> alquilado = estaAlquilado(vehiculo);
		Boolean vendido  = estaVendido(vehiculo);
		if(Boolean.TRUE.equals(vendido)) {
			model.put("esVenta", vendido);
			log.info("El vehículo ya está vendido y no se ha podido realizar la compra");
			return ERROR_VIEW;
		}else if(alquilado.containsKey(true)) {
			model.put("esAlquilerVenta", true);
			model.put("fecha", alquilado.get(true));
			log.info("El vehículo está ya alquilado y no se ha podido realizar el alquiler");
			return ERROR_VIEW;
		}else if(estaEnRevision(vehiculo)){
			model.put("enRevision", estaEnRevision(vehiculo));
			model.put("esRevisionVenta", true);
			log.info("El vehículo está en revisión y no se ha podido realizar la compra");
			return ERROR_VIEW;
		}else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username;
			if(principal instanceof UserDetails) {
				 username = ((UserDetails)principal).getUsername();
			}else {
				 username = principal.toString();
			}
			
			//Obtener cliente logueado y vehiculo
			Cliente cliente = this.clienteService.findClienteByUsername(username);
			//Comprobamos que no ha hecho ninguna compra hace menos de 30 días
			Boolean compraHaceMenosDe30Dias = compraHaceMenosDe30Dias(cliente);
			if (compraHaceMenosDe30Dias) {
				model.put("compraHaceMenosDe30Dias", true);
				return ERROR_VIEW;
			}
			vehiculo.setDisponible(this.vehiculosService.findDisponibleById(5));
			this.vehiculosService.saveVehiculo(vehiculo);
			//Crear venta
			Venta nuevaVenta = new Venta();
			nuevaVenta.setFecha(LocalDate.now());
			nuevaVenta.setCliente(cliente);
			nuevaVenta.setVehiculo(vehiculo);
			nuevaVenta.setReserva(null);
			ventaService.saveVenta(nuevaVenta);
			
			log.info("Este vehículo con id: " + vehiculoId + " ha sido comprado ");
			
			return "redirect:/MisVentas";
		}
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
	
	public boolean compraHaceMenosDe30Dias(Cliente cliente) {
		List<Venta> ventas = cliente.getVentas();
		long diferencia = DAYS.between(ventas.get(0).getFecha(), LocalDate.now());
		if (diferencia < 30) {
			return true;
		}
		return false;
		
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

}
