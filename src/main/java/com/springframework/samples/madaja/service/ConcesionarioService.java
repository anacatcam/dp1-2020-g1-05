package com.springframework.samples.madaja.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.repository.ConcesionarioRepository;

@Service
public class ConcesionarioService {
	
	private ConcesionarioRepository concesionarioRepository;
	
	@Autowired
	public ConcesionarioService(ConcesionarioRepository concesionarioRepository) {
		this.concesionarioRepository=concesionarioRepository;
	}
	
	@Transactional(readOnly = true)
	public Collection<Concesionario> findAllConcesionarios() throws DataAccessException {
		return concesionarioRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Concesionario findConcesionarioById(int id) throws DataAccessException {
		return concesionarioRepository.findById(id);
	}
	
	@Transactional
	public void saveConcesionario(Concesionario concesionario) {
		concesionarioRepository.save(concesionario);
	}

}
