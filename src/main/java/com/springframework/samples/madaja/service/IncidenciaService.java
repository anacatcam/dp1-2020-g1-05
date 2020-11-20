package com.springframework.samples.madaja.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.repository.IncidenciaRepository;

@Service
public class IncidenciaService {
	
	private IncidenciaRepository incidenciaRepository;
	
	public IncidenciaService(IncidenciaRepository incidenciaRepository) {
		this.incidenciaRepository=incidenciaRepository;
	}

	@Transactional
	public void saveIncidencia(Incidencia incidencia)  throws DataAccessException{
		incidenciaRepository.save(incidencia);
	}

	@Transactional(readOnly = true)
	public Incidencia findIncidenciaById(int id) throws DataAccessException {
		return incidenciaRepository.findById(id);
	}

	public Collection<Incidencia> findAllIncidencia() {
		return incidenciaRepository.findAll();
	}

}
