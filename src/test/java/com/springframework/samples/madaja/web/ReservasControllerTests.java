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
import java.util.List;
import java.util.Optional;

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
	
	List<Reserva> reservas= new ArrayList<Reserva>();
	List<Alquiler> alquileres= new ArrayList<Alquiler>();
	List<Venta>ventas= new ArrayList<Venta>();

	
	@BeforeEach
	void setUp() {
		
		usuario = new User();
		usuario.setUsername("alejandro");
		usuario.setEnabled(Boolean.TRUE);
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
		reserva.setFechaGastos(LocalDate.of(2016, 9, 3));
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
	
	@WithMockUser(value = "spring") //ESTÁ BIEN SIN /? RESERVAS EN VEZ DE /RESERVAS y EN LOS OTROS LOS LLEVA??
	@Test
	void testReservasCliente() throws Exception{

		mockMvc.perform(get("/reservas/{dniCliente}",1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("reservas"))
		.andExpect(model().attribute("reservas", reservas))
		.andExpect(view().name("reservas/mostrarReservas"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testBorrarReserva() throws Exception{
		
		mockMvc.perform(get("/reservas/delete/{reservaId}",1))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("ventas"))
		.andExpect(model().attribute("ventas", ventas))
		.andExpect(model().attributeExists("alquileres"))
		.andExpect(model().attribute("alquileres", alquileres))
		.andExpect(view().name("reservas/mostrarReservas"));
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessReservarVehiculoSuccess() throws Exception{
//		
//	}
	

	@WithMockUser(value = "spring")  //Se lo dejo a Manu que no entiendo el reservar{tipo} //NOTA DE MANU: no tengo cojones de sacarlo xdd
	@Test
	void testProcessReservarVehiculoErrors() throws Exception{
		mockMvc.perform(post("reservas/{vehiculoId}/reservar/{tipo}",1,"alquiler")
				.param("fechaGastos", "2016-09-03")
				.param("fianza", "234.")
				.with(csrf())
				.param("alquiler", "")
				.param("venta", "")
				.param("cliente", ""))
		.andExpect(model().attributeHasErrors("reservas"))
		.andExpect(model().attributeHasFieldErrors("reservas","alquiler"))
		.andExpect(model().attributeHasFieldErrors("reservas","venta"))
		.andExpect(model().attributeHasFieldErrors("reservas","cliente"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/reservas"));		
	}
	
}