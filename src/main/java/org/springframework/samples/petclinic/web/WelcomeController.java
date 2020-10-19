package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	 
		  
		  List<Person> personas = new ArrayList<Person>();
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
		  persona3.setLastName("Pérez");
		  personas.add(persona3);
		  Person persona4 = new Person();
		  persona4.setFirstName("Manuel ");
		  persona4.setLastName("Pérez Carrillo");
		  personas.add(persona4);
		  Person persona5 = new Person();
		  persona5.setFirstName("Daniel ");
		  persona5.setLastName("[Apellidos]");
		  personas.add(persona5);
		  Person persona6 = new Person();
		  persona6.setFirstName("Alejandro ");
		  persona6.setLastName("[Apellidos]");
		  personas.add(persona6);
		  model.put("personas", personas);
		  model.put("titulo", "dp1-2020-g1-05");
		  model.put("grupo", "G1-05");

	    return "welcome";
	  }
}
