package com.springframework.samples.madaja.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Person;
import com.springframework.samples.madaja.service.ClienteService;

@Controller
public class WelcomeController {
	 
	  private final ClienteService clienteService;
	
	  @Autowired
	  public WelcomeController(ClienteService clienteService) {
	    this.clienteService = clienteService;
	  }
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {
		  
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		String autoridad="";
		if(principal instanceof UserDetails) {
			 autoridad = ((UserDetails)principal).getAuthorities().iterator().next().toString();
			 username = ((UserDetails)principal).getUsername();
		}else {
			 username = principal.toString();
		}
		if(autoridad.equals("cliente")) {
			Cliente cliente = clienteService.findClienteByUsername(username);
			Integer clienteId=cliente.getId();
			model.put("clienteId", clienteId);
		}
		  
		  List<Person> personas = new ArrayList<>();
		  Person persona = new Person();
		  persona.setFirstName("Juan José ");
		  persona.setLastName("Pérez Plata");
		  personas.add(persona);
		  Person persona2 = new Person();
		  persona2.setFirstName("Álvaro ");
		  persona2.setLastName("Echegoyán Delgado");
		  personas.add(persona2);
		  Person persona3 = new Person();
		  persona3.setFirstName("Antonio ");
		  persona3.setLastName("Pérez Vázquez");
		  personas.add(persona3);
		  Person persona4 = new Person();
		  persona4.setFirstName("Manuel ");
		  persona4.setLastName("Pérez Carrillo");
		  personas.add(persona4);
		  Person persona5 = new Person();
		  persona5.setFirstName("Daniel ");
		  persona5.setLastName("Toro Valle");
		  personas.add(persona5);
		  Person persona6 = new Person();
		  persona6.setFirstName("Alejandro ");
		  persona6.setLastName("Piury Pinzón");
		  personas.add(persona6);
		  model.put("personas", personas);
		  model.put("titulo", "MADAJA");
		  model.put("grupo", "G1-05");

	    return "welcome";
	  }
}
