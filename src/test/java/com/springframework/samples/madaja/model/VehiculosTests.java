package com.springframework.samples.madaja.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class VehiculosTests {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void validacionNotEmptyNegative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vehiculos vehiculo = new Vehiculos();
		vehiculo.setMatricula("");
		vehiculo.setPrecioAlquiler(300);
		vehiculo.setPrecioVenta(2000);
		vehiculo.setMarca("");
		vehiculo.setModelo("");
		vehiculo.setPuertas(4);
		vehiculo.setPlazas(5);
		vehiculo.setMaletero(1);
		vehiculo.setKmActuales(1000);
		vehiculo.setCaracteristicas("");
		vehiculo.setEstado("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Vehiculos>> constraintViolations = validator.validate(vehiculo);
		assertEquals(constraintViolations.size(), 5);
	}
	
	@Test
	void validacionNotEmptyPositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vehiculos vehiculo = new Vehiculos();
		vehiculo.setMatricula("4582 DDN");
		vehiculo.setPrecioAlquiler(300);
		vehiculo.setPrecioVenta(2000);
		vehiculo.setMarca("Opel");
		vehiculo.setModelo("Corsa");
		vehiculo.setPuertas(4);
		vehiculo.setPlazas(5);
		vehiculo.setMaletero(1);
		vehiculo.setKmActuales(1000);
		vehiculo.setCaracteristicas("Consume poco");
		vehiculo.setEstado("Seminuevo");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Vehiculos>> constraintViolations = validator.validate(vehiculo);
		assertEquals(constraintViolations.size(), 0);
	}
	
	@Test
	void validacionPositiveNegative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vehiculos vehiculo = new Vehiculos();
		vehiculo.setMatricula("4582 DDN");
		vehiculo.setPrecioAlquiler(0);
		vehiculo.setPrecioVenta(-5000);
		vehiculo.setMarca("Opel");
		vehiculo.setModelo("Corsa");
		vehiculo.setPuertas(0);
		vehiculo.setPlazas(-5);
		vehiculo.setMaletero(1);
		vehiculo.setKmActuales(1000);
		vehiculo.setCaracteristicas("Consume poco");
		vehiculo.setEstado("Seminuevo");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Vehiculos>> constraintViolations = validator.validate(vehiculo);
		assertEquals(constraintViolations.size(), 4);
	}
	
	@Test
	void validacionPositivePositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vehiculos vehiculo = new Vehiculos();
		vehiculo.setMatricula("4582 DDN");
		vehiculo.setPrecioAlquiler(500);
		vehiculo.setPrecioVenta(null);
		vehiculo.setMarca("Opel");
		vehiculo.setModelo("Corsa");
		vehiculo.setPuertas(3);
		vehiculo.setPlazas(5);
		vehiculo.setMaletero(1);
		vehiculo.setKmActuales(1000);
		vehiculo.setCaracteristicas("Consume poco");
		vehiculo.setEstado("Seminuevo");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Vehiculos>> constraintViolations = validator.validate(vehiculo);
		assertEquals(constraintViolations.size(), 0);
	}
	
	@Test
	void validacionPositiveOrZeroNegative() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vehiculos vehiculo = new Vehiculos();
		vehiculo.setMatricula("4582 DDN");
		vehiculo.setPrecioAlquiler(500);
		vehiculo.setPrecioVenta(null);
		vehiculo.setMarca("Opel");
		vehiculo.setModelo("Corsa");
		vehiculo.setPuertas(3);
		vehiculo.setPlazas(5);
		vehiculo.setMaletero(-1);
		vehiculo.setKmActuales(-1000);
		vehiculo.setCaracteristicas("Consume poco");
		vehiculo.setEstado("Seminuevo");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Vehiculos>> constraintViolations = validator.validate(vehiculo);
		assertEquals(constraintViolations.size(), 2);
	}
	
	@Test
	void validacionPositiveOrZeroPositive() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Vehiculos vehiculo = new Vehiculos();
		vehiculo.setMatricula("4582 DDN");
		vehiculo.setPrecioAlquiler(500);
		vehiculo.setPrecioVenta(null);
		vehiculo.setMarca("Opel");
		vehiculo.setModelo("Corsa");
		vehiculo.setPuertas(3);
		vehiculo.setPlazas(5);
		vehiculo.setMaletero(null);
		vehiculo.setKmActuales(0);
		vehiculo.setCaracteristicas("Consume poco");
		vehiculo.setEstado("Seminuevo");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Vehiculos>> constraintViolations = validator.validate(vehiculo);
		assertEquals(constraintViolations.size(), 0);
	}
}
