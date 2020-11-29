package com.springframework.samples.madaja.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class AlquilerTests {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void validacionLimiteKMPositivoPositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Alquiler alquiler = new Alquiler();
		alquiler.setDepLleno(true);
		alquiler.setLimiteKM(1);
		Validator validator = createValidator();
		Set<ConstraintViolation<Alquiler>> constraintViolations = validator.validate(alquiler);
		assertEquals(constraintViolations.size(), 0);
	}

	@Test
	void validacionLimiteKMPositivoNegative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Alquiler alquiler = new Alquiler();
		alquiler.setDepLleno(true);
		alquiler.setLimiteKM(0);
		Validator validator = createValidator();
		Set<ConstraintViolation<Alquiler>> constraintViolations = validator.validate(alquiler);
		assertEquals(constraintViolations.size(), 2);
	}

	@Test
	void validacionDepLlenoNotNullPositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Alquiler alquiler = new Alquiler();
		alquiler.setDepLleno(true);
		Validator validator = createValidator();
		Set<ConstraintViolation<Alquiler>> constraintViolations = validator.validate(alquiler);
		assertEquals(constraintViolations.size(), 0);
	}

	@Test
	void validacionDepLlenoNotNullNegative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Alquiler alquiler = new Alquiler();
		alquiler.setDepLleno(null);
		Validator validator = createValidator();
		Set<ConstraintViolation<Alquiler>> constraintViolations = validator.validate(alquiler);
		assertEquals(constraintViolations.size(), 1);
	}

}
