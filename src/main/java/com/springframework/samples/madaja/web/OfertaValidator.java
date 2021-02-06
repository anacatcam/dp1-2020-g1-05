package com.springframework.samples.madaja.web;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.Validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springframework.samples.madaja.model.Oferta;

public class OfertaValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Oferta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Oferta oferta = (Oferta) target;
		String titulo = oferta.getName();
		if(titulo.length() == 0 || titulo.length() > 20) {
			errors.rejectValue("name", "requiere un titulo de máximo 20 caracteres", "Requiere un titulo de máximo 20 caracteres");
		}
		if(oferta.getDescuento() == null || oferta.getDescuento() < 0) {
			errors.rejectValue("descuento", "descuento incorrecto", "Descuento incorrecto");
		}
		if(oferta.getFechaLimite() == null ) {
			errors.rejectValue("fechaLimite", "fecha incorrecta: yyyy-mm-dd", "Fecha incorrecta: yyyy-mm-dd");
		}
		if(oferta.getHoraLimite() == null) {
			errors.rejectValue("horaLimite", "hora incorrecta: hh:mm:ss", "Hora incorrecta: hh:mm:ss");
		}
	}
	
	
}
