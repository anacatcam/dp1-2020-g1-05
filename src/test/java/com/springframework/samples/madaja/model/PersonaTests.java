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
	void validacionNotEmpty() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Persona persona = new Persona();
		persona.setNombre("");
		persona.setApellidos("Piury");
		persona.setTelefono("608960166");
		persona.setEmail("alejandropiuryp@gmail.com");
		
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Persona>> constraintViolations = validator.validate(persona);
		System.out.println(constraintViolations.size());
		assertThat(constraintViolations.size()).isEqualTo(1);
	}
	
}
