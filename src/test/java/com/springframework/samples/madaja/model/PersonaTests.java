package com.springframework.samples.madaja.model;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class PersonaTests {
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void validacionNotEmptyNegative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Persona persona = new Persona();
		persona.setDni("49958021C");
		persona.setNombre("");
		persona.setApellidos("");
		persona.setTelefono("");
		persona.setEmail("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Persona>> constraintViolations = validator.validate(persona);
		assertThat(constraintViolations.size()).isEqualTo(6);

	}
	
	@Test
	void validacionNotEmptyPositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Persona persona = new Persona();
		persona.setDni("49958021C");
		persona.setNombre("Alejandro");
		persona.setApellidos("Piury");
		persona.setTelefono("640126156");
		persona.setEmail("alejandro@gmail.com");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Persona>> constraintViolations = validator.validate(persona);
		assertThat(constraintViolations.size()).isEqualTo(0);
		

	}
	
	@Test
	void validacionTelefonoNegative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Persona persona = new Persona();
		persona.setDni("49958021C");
		persona.setNombre("Alejandro");
		persona.setApellidos("Piury");
		persona.setTelefono("1");
		persona.setEmail("alejandro@gmail.com");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Persona>> constraintViolations = validator.validate(persona);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Persona> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("telefono");
		assertThat(violation.getMessage()).isEqualTo("length must be between 9 and 9");
	}
	
	@Test
	void validacionTelefonoPositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Persona persona = new Persona();
		persona.setDni("49958021C");
		persona.setNombre("Alejandro");
		persona.setApellidos("Piury");
		persona.setTelefono("640126156");
		persona.setEmail("alejandro@gmail.com");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Persona>> constraintViolations = validator.validate(persona);
		assertThat(constraintViolations.size()).isEqualTo(0);
	}
	
	@Test
	void validacionEmailNegative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Persona persona = new Persona();
		persona.setDni("49958021C");
		persona.setNombre("Alejandro");
		persona.setApellidos("Piury");
		persona.setTelefono("640126156");
		persona.setEmail("ale");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Persona>> constraintViolations = validator.validate(persona);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Persona> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("email");
		assertThat(violation.getMessage()).isEqualTo("must be a well-formed email address");
	}
	
	@Test
	void validacionEmailPositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Persona persona = new Persona();
		persona.setDni("49958021C");
		persona.setNombre("Alejandro");
		persona.setApellidos("Piury");
		persona.setTelefono("640126156");
		persona.setEmail("ale@gmail.com");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Persona>> constraintViolations = validator.validate(persona);
		assertThat(constraintViolations.size()).isEqualTo(0);
	}
}
