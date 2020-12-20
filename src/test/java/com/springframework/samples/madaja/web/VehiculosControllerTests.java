package com.springframework.samples.madaja.web;

import java.time.LocalDate;
import java.time.LocalTime;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.springframework.samples.madaja.configuration.SecurityConfiguration;
import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Cambio;
import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Combustible;
import com.springframework.samples.madaja.model.Compania;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.Envio;
import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Mecanico;
import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.model.Recogida;
import com.springframework.samples.madaja.model.Reserva;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.service.VehiculosService;
import com.springframework.samples.madaja.web.VehiculosController;

/**
 * Test class for the {@link VehiculosController}
 */

@WebMvcTest(value=VehiculosController.class /*,
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class*/)
//@SpringBootApplication
class VehiculosControllerTests {

	@Autowired
	private VehiculosController vehiculosController;
	
	@MockBean
	private VehiculosService vehiculosService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		Vehiculos vehiculo = new Vehiculos();
		vehiculo.setId(5);
		vehiculo.setMatricula("6874 KJU");
		vehiculo.setPrecioAlquiler(432);
		vehiculo.setPrecioVenta(13000);
		vehiculo.setMarca("Lamborghini");
		vehiculo.setModelo("Gallardo");
		vehiculo.setPuertas(2);
		vehiculo.setPlazas(4);
		vehiculo.setMaletero(100);
		vehiculo.setKmActuales(1000);
		vehiculo.setCaracteristicas("Espacioso / Amplio");
		vehiculo.setEstado("Nuevo");
		Cambio cambio = new Cambio();
		cambio.setId(1);
		cambio.setName("automático");
		vehiculo.setCambio(cambio);
		Disponible disp = new Disponible();
		disp.setId(2);
		disp.setName("venta");
		vehiculo.setDisponible(disp);
		Combustible comb = new Combustible();
		comb.setId(2);
		comb.setName("diesel");
		Concesionario conc = new Concesionario();
		conc.setId(1);
		conc.setCodigoPostal("41063");
		conc.setDireccion("Calle Los Pacos");
		conc.setEmail("concesionario1@gmail.com");
		conc.setLocalidad("Lora del Río");
		conc.setProvincia("Sevilla");
		conc.setPais("España");
		conc.setTelefono("608555102");
		vehiculo.setConcesionario(conc);
		SeguroVehiculo seg = new SeguroVehiculo();
		seg.setId(4);
		seg.setNumeroPoliza("32151");
		seg.setPrecio(450.32);
		seg.setFranquicia(200);
		seg.setCobertura("A todo riesgo");
		seg.setFechaInicio(LocalDate.of(2020, 9, 07));
		seg.setFechaFin(LocalDate.of(2021, 9, 07));
		Compania comp = new Compania();
		comp.setId(2);
		comp.setNombre("Better Call Saúl");
		comp.setTelefono("684525319");
		comp.setEmail("saul@gmail.com");
		seg.setCompania(comp);
		vehiculo.setSeguroVehiculo(seg);
		Incidencia inc = new Incidencia();
		inc.setId(3);
		inc.setDescripcion("Espejillo derecho arrancado");
		inc.setSolucionada(true);
		inc.setVehiculos(vehiculo);
		vehiculo.addIncidencia(inc);
		Alquiler alq = new Alquiler();
		alq.setId(2);//
		Cliente client = new Cliente();
		client.setDni("31998039W");
		client.setNombre("Daniel");
		client.setApellidos("Barranco Llanos");
		client.setTelefono("660257585");
		client.setEmail("danBarll@gmail.com");
		client.setEsConflictivo("falso"); /////////////////////////////////////
		alq.setCliente(client);
		Envio env = new Envio();
		env.setId(2);
		env.setProvincia("Huelva");
		env.setLocalidad("Huelva");
		env.setDireccion("C/San Pedro");
		env.setCodigoPostal("21004");
		env.setPais("España");
		env.setHora(LocalTime.of(11, 00));
		Mecanico mec = new Mecanico();
		mec.setDni("47565973E");
		mec.setNombre("Álvaro");
		mec.setApellidos("Molinas Trujillo");
		mec.setTelefono("625496828");
		mec.setEmail("alvmoltrujillo@gmail.com");
		mec.setSueldo(1730.);
		env.setMecanico(mec);
		env.setAlquiler(alq);
		alq.setEnvio(env);
		Recogida recog = new Recogida();
		recog.setId(2);
		recog.setProvincia("Huelva");
		recog.setLocalidad("Huelva");
		recog.setDireccion("C/San Pedro");
		recog.setCodigoPostal("21004");
		recog.setPais("España");
		recog.setHora(LocalTime.of(13, 00));
		recog.setMecanico(mec);
		alq.setRecogida(recog);
		Reserva res = new Reserva();
		res.setId(5);
		res.setFechaGastos(LocalDate.of(2020, 9, 7));
		res.setFianza(120.0);
		res.setCliente(client);
		alq.setReserva(res);
		alq.setVehiculo(vehiculo);
		alq.setFechaInicio(LocalDate.of(2020, 9, 07));
		alq.setFechaFin(LocalDate.of(2020, 12, 16));
		alq.setLimiteKM(800);
		vehiculo.addAlquiler(alq);
		given(this.vehiculosService.findAllVehiculos()).willReturn(Lists.newArrayList(vehiculo));
	}
	
	@WithMockUser(value= "spring")
		@Test
	void testShowVehiculosDisponibleListHtml() throws Exception{
//		mockMvc.perform(get("/vehiculos/disponible/{disponibleId}")).andExpect(model().attributeExists("vehiculos"))
//		.andExpect(model().attributeExists("disponible")).andExpect(view().name("vehiculos/mostrarVehiculos"));
//	
		mockMvc.perform(get("/vehiculos/disponible/{disponibleId}")).andExpect(status().isOk()).andExpect(model().attributeExists("vehiculos"))
		.andExpect(model().attributeExists("disponible")).andExpect(view().name("vehiculos/mostrarVehiculos"));
	}
}
