package com.springframework.samples.madaja.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.springframework.samples.madaja.model.Concesionario;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ConcesionarioServiceTests {
	
	@Autowired
	protected ConcesionarioService concesionarioService;
	
	@Test
    void shouldFindAllConcesionarios() {
        Iterable<Concesionario> concesionarios = this.concesionarioService.findAllConcesionarios();
        Iterator<Concesionario> concesionario_it= concesionarios.iterator();
        Boolean encontrado = false;
        while(concesionario_it.hasNext()) {
            Concesionario concesionario= concesionario_it.next();
            if(concesionario.getId() == 1) {
                encontrado = true;
                break;
            }
        }
        assertTrue(encontrado);
    }
	
	@Test
	void shouldFindConcesioarioById() {
		Concesionario concesionario = this.concesionarioService.findConcesionarioById(1);
		assertFalse(concesionario.getDireccion().isEmpty());
	}
	
	@Test
	void shouldSaveConcesionario() {
		Collection<Concesionario> concesionarios = this.concesionarioService.findAllConcesionarios();
        int encontrado= concesionarios.size();
        
		Concesionario concesionario = new Concesionario();
		concesionario.setPais("España");
		concesionario.setProvincia("Sevilla");
		concesionario.setLocalidad("Sevilla");
		concesionario.setDireccion("Ctra. Su Eminencia, 2");
		concesionario.setCodigoPostal("41006");
		concesionario.setEmail("concesionarioPrueba@gmail.com");
		concesionario.setTelefono("954933230");
		
		this.concesionarioService.saveConcesionario(concesionario);
		assertNotEquals(concesionario.getId().longValue(),0);
		
		concesionarios=this.concesionarioService.findAllConcesionarios();
		assertEquals(concesionarios.size(), encontrado+1);
	
	}

}
