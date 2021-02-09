package com.springframework.samples.madaja.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ReservaTests {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void validacionFianzaMin1Positive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Reserva reserva = new Reserva();
		reserva.setFianza(50.0);
		reserva.setFechaGastos(null);
		Validator validator = createValidator();
		Set<ConstraintViolation<Reserva>> constraintViolations = validator.validate(reserva);
		assertEquals(constraintViolations.size(), 0);
	}

	@Test
	void validacionFianzaMin1Negative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Reserva reserva = new Reserva();
		reserva.setFianza(0.0);
		reserva.setFechaGastos(null);
		Validator validator = createValidator();
		Set<ConstraintViolation<Reserva>> constraintViolations = validator.validate(reserva);
		assertEquals(constraintViolations.size(), 1);
	}

//	@Test
//	void validacionFormatoFechaPositive() {
//		LocaleContextHolder.setLocale(Locale.ENGLISH);			
//		Reserva reserva = new Reserva();
//		reserva.setFianza(null);
//		reserva.setFechaGastos(LocalDate.of(2020, 11, 11));			
//		Validator validator = createValidator();
//		Set<ConstraintViolation<Reserva>> constraintViolations = validator.validate(reserva);
//		assertEquals(constraintViolations.size(),0);
//	}

//	@Test
//	void validacionFormatoFechaNegative() {
//		LocaleContextHolder.setLocale(Locale.ENGLISH);
//		Reserva reserva = new Reserva();
//		reserva.setFianza(null);
//		reserva.setFechaGastos(LocalDate.of(11, 11, 2020));
//		Validator validator = createValidator();
//		Set<ConstraintViolation<Reserva>> constraintViolations = validator.validate(reserva);
//		assertEquals(constraintViolations.size(), 1);
//	}

}
