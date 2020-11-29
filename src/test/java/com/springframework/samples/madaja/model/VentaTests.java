package com.springframework.samples.madaja.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class VentaTests {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void validacionVentaPositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Venta venta = new Venta();
		venta.setFecha(LocalDate.of(2020, 11, 11));
		Validator validator = createValidator();
		Set<ConstraintViolation<Venta>> constraintViolations = validator.validate(venta);
		assertEquals(constraintViolations.size(), 0);
	}

//	@Test
//	void validacionVentaNegative() {
//		LocaleContextHolder.setLocale(Locale.ENGLISH);
//		Venta venta = new Venta();
//		Validator validator = createValidator();
//		Set<ConstraintViolation<Venta>> constraintViolations = validator.validate(venta);
//		assertEquals(constraintViolations.size(), 0);
//	}

}