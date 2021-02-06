package com.springframework.samples.madaja.web;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Lists;
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
import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Authorities;
import com.springframework.samples.madaja.model.Cambio;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Combustible;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.Reserva;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.User;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.OfertaService;
import com.springframework.samples.madaja.service.ReservaService;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.service.VentaService;

@WebMvcTest(controllers=ReservasController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ReservasControllerTests {
	

	@Autowired
	private ReservasController reservaController;
	
	@MockBean
	private VehiculosService vehiculosService;
	
	@MockBean
	private ReservaService reservaService;
	
	@MockBean
	private AlquilerService alquilerService;
	
	@MockBean
	private VentaService ventaService;
	
	@MockBean
	private ClienteService clienteService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private User usuario;
	private Cliente cliente;
	private Reserva reserva;
	private Venta venta;
	private Alquiler alquiler;
	private Vehiculos vehiculo;
	private SeguroVehiculo seguroVehiculo;
	private Oferta oferta;
	private Disponible disponible;
	private Concesionario concesionario;
	private Combustible combustible;
	private Cambio cambio;
	private Authorities authority;
	
	List<Reserva> reservas= new ArrayList<Reserva>();
	List<Alquiler> alquileres= new ArrayList<Alquiler>();
	List<Venta>ventas= new ArrayList<Venta>();

	
	@BeforeEach
	void setUp() {
		
		authority = new Authorities();
		authority.setId(1);
		authority.setAuthority("cliente");
		
		Set<Authorities> authorities = new HashSet<>();
		authorities.add(authority);
		
		usuario = new User();
		usuario.setUsername("alejandro");
		usuario.setEnabled(Boolean.TRUE);
		usuario.setAuthorities(authorities);
		usuario.setPassword("contraseña3");
		
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
		reserva.setFechaGastos(LocalDate.of(2021, 9, 3));
		reserva.setFianza(234.);
		reserva.setCliente(cliente);
		
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
		concesionario.setLocalidad("Lora del Río");
		concesionario.setProvincia("Sevilla");
		concesionario.setPais("España");
		concesionario.setTelefono("608555102");
		
		combustible = new Combustible();
		combustible.setId(1);
		combustible.setName("diesel");
		
	    cambio = new Cambio();
		cambio.setId(1);
		cambio.setName("automático");
		
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
		
		alquiler = new Alquiler();
		alquiler.setId(1);
		alquiler.setFechaInicio(LocalDate.of(2020, 12, 21));
		alquiler.setFechaFin(LocalDate.of(2021, 3, 2));
		alquiler.setLimiteKM(20000);
		alquiler.setDepLleno(false);
		alquiler.setCliente(cliente);
		alquiler.setReserva(reserva);
		alquiler.setVehiculo(vehiculo);
		
		venta = new Venta();
		venta.setId(1);
		venta.setFecha(LocalDate.of(2020, 12, 22));
		venta.setCliente(cliente);
		venta.setReserva(reserva);
		venta.setVehiculo(vehiculo);
		
		reservas.add(reserva);
		alquileres.add(alquiler);
		ventas.add(venta);
		
		given(alquilerService.findAllAlquiler()).willReturn(alquileres);
		given(ventaService.findAllVentas()).willReturn(ventas);
		given(reservaService.findAllReserva()).willReturn(reservas);
		given(reservaService.findByDNI(anyString())).willReturn(reservas);
		given(reservaService.findReservaById(anyInt())).willReturn(Optional.of(reserva));
		given(clienteService.findClienteByUsername(anyString())).willReturn(cliente);
		given(vehiculosService.findVehiculoById(anyInt())).willReturn(vehiculo);
		given(vehiculosService.findDisponibleById(anyInt())).willReturn(disponible);
		given(alquilerService.findAlquilerByDni(anyString())).willReturn(alquileres);
		given(ventaService.findVentasByDni(anyString())).willReturn(ventas);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testMostrarMisReservas() throws Exception{

		mockMvc.perform(get("/reservas/mis-reservas"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("alquileres"))
		.andExpect(model().attributeExists("ventas"))
		.andExpect(model().attribute("alquileres", alquileres))
		.andExpect(model().attribute("ventas", ventas))
		.andExpect(view().name("/reservas/mostrarMisReservas"));
	}
	
	@WithMockUser(value = "spring") 
	@Test
	void testMostrarReservas() throws Exception{

		mockMvc.perform(get("/reservas"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("ventas"))
		.andExpect(model().attribute("ventas", ventas))
		.andExpect(model().attributeExists("alquileres"))
		.andExpect(model().attribute("alquileres", alquileres))
		.andExpect(view().name("/reservas/mostrarReservas"));
	}
	
	/*Al tener condicionales no puede revisar si existe el atributo en el modelo, de igual forma la vista a devolver 
	se actualiza en el condicional, por lo que devolvemos a oups por defecto que significa que la persona que 
	ha intentado acceder no es cliente ni admin*/
	@WithMockUser(value = "spring")
	@Test
	void testBorrarReserva() throws Exception{
		
		mockMvc.perform(get("/reservas/{reservaId}/delete",1))
		.andExpect(status().isOk())
		.andExpect(model().hasNoErrors())
		.andExpect(view().name("/oups"));
	}
	
	
	@WithMockUser(value = "spring")
	@Test
	void testNuevaReserva() throws Exception{

		mockMvc.perform(get("/reservas/nuevaReserva/{vehiculoId}", 1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("cliente"))
		.andExpect(model().attribute("cliente", cliente))
		.andExpect(model().attributeExists("vehiculo"))
		.andExpect(model().attribute("vehiculo", vehiculo))
		.andExpect(view().name("reservas/seleccionarReserva"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testNuevaReservaConflictivo() throws Exception{

		Cliente clienteConflictivo = new Cliente();
		clienteConflictivo.setId(1);
		clienteConflictivo.setFirstName("Alejandro");
		clienteConflictivo.setLastName("Castellano Sanz");
		clienteConflictivo.setDni("12422051G");
		clienteConflictivo.setEmail("alejcastz@gmail.com");
		clienteConflictivo.setEsConflictivo("Si");
		clienteConflictivo.setTelefono("637666517");
		clienteConflictivo.setUser(usuario);
		
		given(clienteService.findClienteByUsername(anyString())).willReturn(clienteConflictivo);
		
		//Rama del if conflictivo: es cliente conflictivo
		
		mockMvc.perform(get("/reservas/nuevaReserva/{vehiculoId}", 1))
		.andExpect(status().isOk())
		.andExpect(model().attribute("esConflictivo", true))
		.andExpect(view().name("operacionImposible"));
	}
	
	
	@WithMockUser(value = "spring")  
	@Test
	void testInitReservarVehiculo() throws Exception{
		
		Reserva nuevaReserva = new Reserva();
		Double fianza = 234.;
		nuevaReserva.setFianza(fianza);
		nuevaReserva.setCliente(cliente);
		
		mockMvc.perform(get("/reservas/{vehiculoId}/reservar/{tipo}", 1, "alquiler"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("reserva"))
		.andExpect(model().attribute("reserva", nuevaReserva))
		.andExpect(model().attributeExists("cliente"))
		.andExpect(model().attribute("cliente", cliente))
		.andExpect(view().name("reservas/crearReservaForm"));
	}
	
	@WithMockUser(value = "Spring")
	@Test
	void testProcessreservarVehiculoSuccess() throws Exception{
		mockMvc.perform(post("/reservas/{vehiculoId}/reservar/{tipo}", 1, "Alquiler")
				.with(csrf())
				.param("id", "1")
				.param("fianza", "234.")
				.param("fechaGastos", "2021-09-03")
				.param("cliente", "1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/reservas/mis-reservas"));
	}
	
	@WithMockUser(value = "Spring")
	@Test
	void testProcessreservarVehiculoError() throws Exception{
		mockMvc.perform(post("/reservas/{vehiculoId}/reservar/{tipo}", 1, "Alquiler")
				.with(csrf())
				.param("id", "1")
				.param("fianza", "234.")
				.param("fechaGastos", "4545546")
				.param("cliente", "1"))
		.andExpect(model().attributeHasErrors("reserva"))
		.andExpect(model().attributeErrorCount("reserva", 1))
		.andExpect(model().attributeHasFieldErrors("reserva", "fechaGastos"))
		.andExpect(status().isOk())
		.andExpect(view().name("reservas/crearReservaForm"));
	}
	
}