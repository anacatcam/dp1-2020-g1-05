package com.springframework.samples.madaja.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Oferta;


public class AlquilerValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Alquiler.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Alquiler alquiler = (Alquiler) target;
		if(alquiler.getFechaFin() == null) {
			errors.rejectValue("fechaFin", "fecha incorrecta: yyyy-mm-dd", 
					"La fecha de finalización no puede estar vacía");
		}
		if(alquiler.getFechaFin().isBefore(alquiler.getFechaInicio())) {
			errors.rejectValue("fechaFin", "fecha incorrecta: yyyy-mm-dd", 
					"La fecha de finalización no puede ser anterior a la fecha de inicio");
		}
		if(alquiler.getFechaFin().isEqual(alquiler.getFechaInicio())) {
			errors.rejectValue("fechaFin", "fecha incorrecta: yyyy-mm-dd", 
					"La fecha de finalización no puede ser la misma que la fecha de inicio");
		}
		if(alquiler.getFechaInicio() == null) {
			errors.rejectValue("fechaInicio", "fecha incorrecta: yyyy-mm-dd", 
					"La fecha de inicio no puede estar vacía");
		}
		if(alquiler.getFechaInicio().isAfter(alquiler.getFechaFin())) {
			errors.rejectValue("fechaInicio", "fecha incorrecta: yyyy-mm-dd", 
					"La fecha de inicio no puede ser posterior a la fecha de finalización");
		}
	}
	
	
}
