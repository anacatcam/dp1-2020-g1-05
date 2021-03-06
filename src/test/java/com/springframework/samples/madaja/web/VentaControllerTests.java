package com.springframework.samples.madaja.web;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.springframework.samples.madaja.configuration.SecurityConfiguration;
import com.springframework.samples.madaja.model.Cambio;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Combustible;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Envio;
import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Mecanico;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.Reserva;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.User;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.service.VentaService;

@WebMvcTest(controllers=VentaController.class,

excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class VentaControllerTests {
  
	@Autowired
	private VentaController ventaController;
	
	@MockBean
	private VentaService ventaService;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private VehiculosService vehiculosService;
	
	@MockBean
	private AlquilerService alquilerService;
	
	@Autowired
	private MockMvc mockMvc;
	 
	private Venta venta;
		
	private Cliente cliente;
		
	private User usuario;
		
	private Mecanico mecanico;
		
	private Reserva reserva;
		
	private Vehiculos vehiculo;
		
	private Cambio cambio;
		
	private Combustible combustible;
		
	private Concesionario concesionario;
		
	private Disponible disponible;
		
	private Oferta oferta;
		
	private SeguroVehiculo seguroVehiculo;
  
  private Envio envio;
	
	@BeforeEach
	 void setUp() {
     
		seguroVehiculo = new SeguroVehiculo();
		seguroVehiculo.setId(1);
		seguroVehiculo.setNumeroPoliza("32151");
		seguroVehiculo.setPrecio(450.32);
		seguroVehiculo.setFranquicia(200);
		seguroVehiculo.setCobertura("A todo riesgo");
		seguroVehiculo.setFechaInicio(LocalDate.of(2020, 9, 07));
		seguroVehiculo.setFechaFin(LocalDate.of(2021, 9, 07));
     
		oferta = new Oferta();
		oferta.setId(1);
		oferta.setName("Oferta 1");
		oferta.setDescuento(22.0);
		oferta.setFechaLimite(LocalDate.of(2020, 6, 12));
		oferta.setHoraLimite(LocalTime.of(2, 3, 4));

		disponible = new Disponible();
		disponible.setId(1);
		disponible.setName("venta");

		concesionario = new Concesionario();
		concesionario.setId(1);
		concesionario.setCodigoPostal("41063");
		concesionario.setDireccion("Calle Los Pacos");
		concesionario.setEmail("concesionario1@gmail.com");
		concesionario.setLocalidad("Lora del R??o");
		concesionario.setProvincia("Sevilla");
		concesionario.setPais("Espa??a");
		concesionario.setTelefono("608555102");

		combustible = new Combustible();
		combustible.setId(1);
		combustible.setName("diesel");
			
		cambio = new Cambio();
		cambio.setId(1);
		cambio.setName("autom??tico");

		vehiculo = new Vehiculos();
		vehiculo.setId(1);
		vehiculo.setCaracteristicas("Espacioso / Amplio");
		vehiculo.setEstado("Nuevo");
		vehiculo.setKmActuales(1000);
		vehiculo.setMaletero(100);
		vehiculo.setMarca("Lamborghini");
		vehiculo.setMatricula("6874 KJU");
		vehiculo.setModelo("Gallardo");
		vehiculo.setPlazas(4);
		vehiculo.setPrecioAlquiler(432);
		vehiculo.setPrecioVenta(13000);
		vehiculo.setPuertas(2);
		vehiculo.setCambio(cambio);
		vehiculo.setCombustible(combustible);
		vehiculo.setConcesionario(concesionario);
		vehiculo.setDisponible(disponible);
		vehiculo.setOferta(oferta);
		vehiculo.setSeguroVehiculo(seguroVehiculo);

		mecanico = new Mecanico();
		mecanico.setDni("47565973E");
		mecanico.setLastName("Molinas Trujillo");
		mecanico.setEmail("alvmoltrujillo@gmail.com");
		mecanico.setFirstName("??lvaro");
		mecanico.setTelefono("625496828");
		mecanico.setSueldo(1730.0);	

		envio = new Envio();
		envio.setId(1);
		envio.setCodigoPostal("41005");
		envio.setDireccion("C/Aznalcazar");
		envio.setLocalidad("Sevilla");
		envio.setPais("Espa??a");
		envio.setProvincia("Sevilla");
		envio.setHora(LocalTime.of(10, 0, 0));
		envio.setFecha(LocalDate.of(2010, 9, 3));
		envio.setMecanico(mecanico);
     
		usuario = new User();
		usuario.setUsername("alejandro");
		usuario.setEnabled(Boolean.TRUE);
		usuario.setPassword("contrase??a3");
     
		cliente = new Cliente();
		cliente.setId(1);
		cliente.setFirstName("Alejandro");
		cliente.setLastName("Castellano Sanz");
		cliente.setDni("12422051G");
		cliente.setEmail("alejcastz@gmail.co");
		cliente.setEsConflictivo("No lo es");
		cliente.setTelefono("637666517");
		cliente.setUser(usuario);
     
		reserva = new Reserva();
		reserva.setId(1);
		reserva.setFechaGastos(LocalDate.of(2016, 9, 3));
		reserva.setFianza(317.8);
		reserva.setCliente(cliente);
		
		venta = new Venta();
		venta.setId(1);
		venta.setEnvio(envio);
		venta.setFecha(LocalDate.of(2020, 12, 21));
		venta.setVehiculo(vehiculo);
		venta.setCliente(cliente);
		venta.setReserva(reserva);
	}
	
	
	@WithMockUser(value = "spring")
	 @Test
	 void testShowAllVentas() throws Exception{
		List<Venta> ventas = new ArrayList<Venta>();
		ventas.add(venta);
		given(ventaService.findAllVentas()).willReturn(ventas);
		 
		 mockMvc.perform(get("/ventas")).andExpect(status().isOk()).andExpect(model().attributeExists("ventas"))
		 .andExpect(model().attribute("ventas", ventas))
		 .andExpect(view().name("/venta/mostrarVentas"));;
	 }
	
	@WithMockUser(value = "Spring")
	@Test
	void testComprarVehiculoIF() throws Exception{		
		//Rama del if: est?? vendido
		List<Venta> ventas = new ArrayList<Venta>();
		ventas.add(venta);
		
		given(ventaService.findAllVentas()).willReturn(ventas);
		given(vehiculosService.findVehiculoById(anyInt())).willReturn(vehiculo);
		
		
		mockMvc.perform(get("/vehiculos/{vehiculoId}/comprar",1)).andExpect(status().isOk())
		.andExpect(model().attribute("esVenta", true))
		.andExpect(view().name("operacionImposible"));
		
		
	}
	
	@WithMockUser(value = "Spring")
	@Test
	void testComprarVehiculoElseIf() throws Exception{
		
		//Rama del else if: est?? en revisi??n
		List<Venta> ventas = new ArrayList<Venta>();
		
		given(ventaService.findAllVentas()).willReturn(ventas);
		given(vehiculosService.findVehiculoById(anyInt())).willReturn(vehiculo);

		
		Incidencia incidencia = new Incidencia();
		incidencia.setId(1);
		incidencia.setDescripcion("Golpe en la parte frontal");
		incidencia.setSolucionada(Boolean.FALSE);
		incidencia.setVehiculos(vehiculo);
		
		vehiculo.addIncidencia(incidencia);
		
		mockMvc.perform(get("/vehiculos/{vehiculoId}/comprar",1)).andExpect(status().isOk())
		.andExpect(model().attribute("enRevision", true))
		.andExpect(model().attribute("esRevisionVenta", true))
		.andExpect(view().name("operacionImposible"));
	}
	
	@WithMockUser(value = "Spring")
	@Test
	void testComprarVehiculoElse() throws Exception{
	
		venta.setVehiculo(null);
		
		given(clienteService.findClienteByUsername(anyString())).willReturn(cliente);
		cliente.addVentas(venta);
		//Rama del else: se puede comprar
		List<Venta> ventas = new ArrayList<Venta>();
		ventas.add(venta);
		given(ventaService.findAllVentas()).willReturn(ventas);
		given(vehiculosService.findVehiculoById(anyInt())).willReturn(vehiculo);

		
		
		mockMvc.perform(get("/vehiculos/{vehiculoId}/comprar",2)).andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/MisVentas"));
	}
}
