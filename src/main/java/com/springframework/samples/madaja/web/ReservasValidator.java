package com.springframework.samples.madaja.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springframework.samples.madaja.model.Reserva;

public class ReservasValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Reserva.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Reserva reserva = (Reserva) target;
		if(reserva.getFianza() <= 0.0 || reserva.getFianza() == null) {
			errors.rejectValue("fianza", "valor incorrecto", "La fianza no puede tener un valor negativo o 0.");
		}
		if(reserva.getFechaGastos() == null) {
			errors.rejectValue("fechaGastos", "fecha incorrecta: yyyy-mm-dd", "Fecha incorrecta: yyyy-mm-dd");
		}
	}

}
