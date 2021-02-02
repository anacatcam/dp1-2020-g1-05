package com.springframework.samples.madaja.web;

import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import com.springframework.samples.madaja.model.Envio;
import com.springframework.samples.madaja.model.Mecanico;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.Recogida;
import com.springframework.samples.madaja.model.Reserva;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.User;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.AlquilerService;
import com.springframework.samples.madaja.service.ClienteService;
import com.springframework.samples.madaja.service.VehiculosService;

@WebMvcTest(controllers=AlquilerController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class AlquilerControllerTests {

    @Autowired
	private AlquilerController alquilerController;
	 
	@MockBean
	private AlquilerService alquilerService;
	 
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private VehiculosService vehiculosService;
	
	@Autowired
	private MockMvc mockMvc;
	 
	private Alquiler alquiler;
		
	private Cliente cliente;
		
	private User usuario;
		
	private Envio envio;
		
	private Mecanico mecanico;
		
	private Recogida recogida;
		
	private Reserva reserva;
		
	private Vehiculos vehiculo;
		
	private Cambio cambio;
		
	private Combustible combustible;
		
	private Concesionario concesionario;
		
	private Disponible disponible;
		
	private Oferta oferta;
		
	private SeguroVehiculo seguroVehiculo;
	 
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
			
			
		mecanico = new Mecanico();
		mecanico.setDni("47565973E");
		mecanico.setApellidos("Molinas Trujillo");
		mecanico.setEmail("alvmoltrujillo@gmail.com");
		mecanico.setNombre("Álvaro");
		mecanico.setTelefono("625496828");
		mecanico.setSueldo(1730.0);		
			
		recogida = new Recogida();
		recogida.setId(1);
		recogida.setCodigoPostal("41005");
		recogida.setDireccion("C/Aznalcazar");
		recogida.setLocalidad("Sevilla");
		recogida.setPais("España");
		recogida.setProvincia("Sevilla");
		recogida.setHora(LocalTime.of(12, 0, 0));
		recogida.setMecanico(mecanico);
			
		envio = new Envio();
		envio.setId(1);
		envio.setCodigoPostal("41005");
		envio.setDireccion("C/Aznalcazar");
		envio.setLocalidad("Sevilla");
		envio.setPais("España");
		envio.setProvincia("Sevilla");
		envio.setHora(LocalTime.of(10, 0, 0));
		envio.setMecanico(mecanico);
			
			
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
		reserva.setFianza(317.8);
		reserva.setCliente(cliente);
			
		alquiler = new Alquiler();
		alquiler.setId(1);
		alquiler.setFechaInicio(LocalDate.of(2020, 12, 21));
		alquiler.setFechaFin(LocalDate.of(2021, 3, 2));
		alquiler.setLimiteKM(20000);
		alquiler.setDepLleno(false);
		alquiler.setCliente(cliente);
		alquiler.setEnvio(envio);
		alquiler.setRecogida(recogida);
		alquiler.setReserva(reserva);
		alquiler.setVehiculo(vehiculo);
	 }
	 
	 @WithMockUser(value = "spring")
	 @Test
	 void testShowMisAlquileres() throws Exception{
		 List<Alquiler> alquileres = new ArrayList<Alquiler>();
		 alquileres.add(alquiler);
		 given(clienteService.findClienteByUsername(anyString())).willReturn(cliente);
		 given(alquilerService.findAlquilerByDni(anyString())).willReturn(alquileres);
		 
		 mockMvc.perform(get("/MisAlquileres")).andExpect(status().isOk()).andExpect(model().attributeExists("alquileres"))
		 .andExpect(model().attribute("alquileres", alquileres))
		 .andExpect(view().name("/alquiler/mostrarMisAlquileres"));
	 }
}
