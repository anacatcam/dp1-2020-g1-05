package com.springframework.samples.madaja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Recogida;
import com.springframework.samples.madaja.repository.RecogidaRepository;

@Service
public class RecogidaService {
	private RecogidaRepository recogidaRepository;
	
	@Autowired
	public RecogidaService(RecogidaRepository recogidaRepository) {
		this.recogidaRepository = recogidaRepository;
	}
	
	@Transactional(readOnly = true)
	public Iterable<Recogida> findAllRecogidas() throws DataAccessException {
		return recogidaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Recogida findRecogidaById(int id) throws DataAccessException{
		return recogidaRepository.findById(id);
	}
	
	@Transactional
	public void saveRecogida(Recogida recogida) {
		recogidaRepository.save(recogida);
	}
}
