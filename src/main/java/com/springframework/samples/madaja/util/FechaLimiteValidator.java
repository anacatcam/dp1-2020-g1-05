package com.springframework.samples.madaja.util;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FechaLimiteValidator implements ConstraintValidator<FechaLimiteConstraint, LocalDate>{

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return value != null && !value.isBefore(LocalDate.now());
	}

}
