package com.springframework.samples.madaja.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


public class IncidenciaTests{
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
}
	
@Test
void validacionDescriptionNotEmptyNegative() {
	LocaleContextHolder.setLocale(Locale.ENGLISH);
	Incidencia incidencia= new Incidencia();
	incidencia.setDescripcion("");
	incidencia.setSolucionada(false);
	
	Validator validator= createValidator();
	Set<ConstraintViolation<Incidencia>> constraintViolations = validator.validate(incidencia);
	assertThat(constraintViolations.size()).isEqualTo(1);
	}

@Test
void validacionDescriptionNotEmptyPositive() {
	LocaleContextHolder.setLocale(Locale.ENGLISH);
	Incidencia incidencia= new Incidencia();
	incidencia.setDescripcion("Golpe en el capó");
	incidencia.setSolucionada(false);
	
	Validator validator= createValidator();
	Set<ConstraintViolation<Incidencia>> constraintViolations = validator.validate(incidencia);
	assertThat(constraintViolations.size()).isEqualTo(0);
	}

@Test
void validacionSolucionadaNotEmptyNegative() {
	LocaleContextHolder.setLocale(Locale.ENGLISH);
	Incidencia incidencia= new Incidencia();
	incidencia.setDescripcion("Pinchazo en rueda posterior izquierda");
	incidencia.setSolucionada(null);
	
	Validator validator= createValidator();
	Set<ConstraintViolation<Incidencia>> constraintViolations = validator.validate(incidencia);
	assertThat(constraintViolations.size()).isEqualTo(1);
	}

@Test
void validacionSolucionadaNotEmptyPositive() {
	LocaleContextHolder.setLocale(Locale.ENGLISH);
	Incidencia incidencia= new Incidencia();
	incidencia.setDescripcion("Pinchazo en rueda posterior izquierda");
	incidencia.setSolucionada(true);
	
	Validator validator= createValidator();
	Set<ConstraintViolation<Incidencia>> constraintViolations = validator.validate(incidencia);
	assertThat(constraintViolations.size()).isEqualTo(0);
	}
}