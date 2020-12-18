package com.springframework.samples.madaja.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ConcesionarioTests {
	
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void validacionNotEmptyNegative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Concesionario concesionario = new Concesionario();
		concesionario.setNombre("");
		concesionario.setEmail("");
		concesionario.setTelefono("");
		concesionario.setCodigoPostal("");
		concesionario.setDireccion("");
		concesionario.setLocalidad("");
		concesionario.setPais("");
		concesionario.setProvincia("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Concesionario>> constraintViolations = validator.validate(concesionario);
		assertEquals(constraintViolations.size(), 10);

	}
	
	@Test
	void validacionNotEmptyPositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Concesionario concesionario = new Concesionario();
		concesionario.setNombre("Systasa");
		concesionario.setEmail("aperezva32@gmail.com");
		concesionario.setTelefono("673580611");
		concesionario.setCodigoPostal("41010");
		concesionario.setDireccion("C/ Castilla, 16");
		concesionario.setLocalidad("Sevilla");
		concesionario.setPais("Españita");
		concesionario.setProvincia("Sevilla");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Concesionario>> constraintViolations = validator.validate(concesionario);
		assertEquals(constraintViolations.size(),0);
		
	}
	
	@Test
	void validacionTelefonoNegative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Concesionario concesionario = new Concesionario();
		concesionario.setNombre("Systasa");
		concesionario.setEmail("aperezva32@gmail.com");
		concesionario.setTelefono("1");
		concesionario.setCodigoPostal("41010");
		concesionario.setDireccion("C/ Castilla, 16");
		concesionario.setLocalidad("Sevilla");
		concesionario.setPais("Españita");
		concesionario.setProvincia("Sevilla");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Concesionario>> constraintViolations = validator.validate(concesionario);
		assertEquals(constraintViolations.size(),1);
		ConstraintViolation<Concesionario> violation = constraintViolations.iterator().next();
		assertEquals(violation.getPropertyPath().toString(), "telefono");
		assertEquals(violation.getMessage(),"length must be between 9 and 9");
	}
	
	@Test
	void validacionTelefonoPositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Concesionario concesionario = new Concesionario();
		concesionario.setNombre("Systasa");
		concesionario.setEmail("aperezva32@gmail.com");
		concesionario.setTelefono("673580611");
		concesionario.setCodigoPostal("41010");
		concesionario.setDireccion("C/ Castilla, 16");
		concesionario.setLocalidad("Sevilla");
		concesionario.setPais("Españita");
		concesionario.setProvincia("Sevilla");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Concesionario>> constraintViolations = validator.validate(concesionario);
		assertEquals(constraintViolations.size(),0);
	}
	
	@Test
	void validacionEmailNegative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Concesionario concesionario = new Concesionario();
		concesionario.setNombre("Systasa");
		concesionario.setEmail("aperezva32");
		concesionario.setTelefono("673580611");
		concesionario.setCodigoPostal("41010");
		concesionario.setDireccion("C/ Castilla, 16");
		concesionario.setLocalidad("Sevilla");
		concesionario.setPais("Españita");
		concesionario.setProvincia("Sevilla");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Concesionario>> constraintViolations = validator.validate(concesionario);
		assertEquals(constraintViolations.size(),1);
		ConstraintViolation<Concesionario> violation = constraintViolations.iterator().next();
		assertEquals(violation.getPropertyPath().toString(),"email");
		assertEquals(violation.getMessage(),"must be a well-formed email address");
	}
	
	@Test
	void validacionEmailPositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Concesionario concesionario = new Concesionario();
		concesionario.setNombre("Systasa");
		concesionario.setEmail("aperezva32@gmail.com");
		concesionario.setTelefono("673580611");
		concesionario.setCodigoPostal("41010");
		concesionario.setDireccion("C/ Castilla, 16");
		concesionario.setLocalidad("Sevilla");
		concesionario.setPais("Españita");
		concesionario.setProvincia("Sevilla");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Concesionario>> constraintViolations = validator.validate(concesionario);
		assertEquals(constraintViolations.size(),0);
	}

}
