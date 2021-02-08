package com.springframework.samples.madaja.model;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateConstraint, Alquiler> {

	public void initialize(DateConstraint date) {

	}

	@Override
	public boolean isValid(Alquiler value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return (value.getFechaInicio().isEqual(LocalDate.now()) || value.getFechaInicio().isAfter(LocalDate.now()))
				&& (value.getFechaFin().isAfter(value.getFechaInicio())
						|| value.getFechaFin().isEqual(value.getFechaInicio()));
	}

}
