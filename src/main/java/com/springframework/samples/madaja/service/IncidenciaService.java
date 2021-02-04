package com.springframework.samples.madaja.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Incidencia;
import com.springframework.samples.madaja.model.Mecanico;
import com.springframework.samples.madaja.repository.IncidenciaRepository;
import com.springframework.samples.madaja.repository.MecanicoRepository;

@Service
public class IncidenciaService {
	
	private IncidenciaRepository incidenciaRepository;
	private MecanicoRepository mecanicoRepository;
	
	@Autowired
	public IncidenciaService(IncidenciaRepository incidenciaRepository, MecanicoRepository mecanicoRepository) {
		this.incidenciaRepository=incidenciaRepository;
		this.mecanicoRepository=mecanicoRepository;
	}

	@Transactional
	public void saveIncidencia(Incidencia incidencia)  throws DataAccessException{
		incidenciaRepository.save(incidencia);
	}
	
	@Transactional(readOnly = true)
	public Incidencia findIncidenciaById(int id) throws DataAccessException {
		return incidenciaRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Incidencia> findAllIncidencia() {
		return incidenciaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Collection<Mecanico> findAllMecanicos() {
		return mecanicoRepository.findAll();
	}

}