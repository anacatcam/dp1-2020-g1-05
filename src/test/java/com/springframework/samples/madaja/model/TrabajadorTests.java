package com.springframework.samples.madaja.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class TrabajadorTests {
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void validacionNotEmptyNegative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Trabajador trabajador = new Trabajador();
		trabajador.setDni("49958021C");
		trabajador.setFirstName("");
		trabajador.setLastName("");
		trabajador.setTelefono("");
		trabajador.setEmail("");
		trabajador.setSueldo(null);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Trabajador>> constraintViolations = validator.validate(trabajador);
		assertThat(constraintViolations.size()).isEqualTo(6);
	}
	
	@Test
	void validacionNotEmptyPositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Trabajador trabajador = new Trabajador();
		trabajador.setDni("49958021C");
		trabajador.setFirstName("Alejandro");
		trabajador.setLastName("Piury");
		trabajador.setTelefono("640126156");
		trabajador.setEmail("ale@gmail.com");
		trabajador.setSueldo(11.0);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Trabajador>> constraintViolations = validator.validate(trabajador);
		assertThat(constraintViolations.size()).isEqualTo(0);
	}
}
