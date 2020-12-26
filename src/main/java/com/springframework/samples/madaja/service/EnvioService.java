package com.springframework.samples.madaja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Envio;
import com.springframework.samples.madaja.repository.EnvioRepository;

@Service
public class EnvioService {
	private EnvioRepository envioRepository;
	
	@Autowired
	public EnvioService(EnvioRepository envioRepository) {
		this.envioRepository = envioRepository;
	}
	
	@Transactional(readOnly = true)
	Iterable<Envio> findAllEnvio() throws DataAccessException{
		return envioRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Envio> findEnvioByConcesionario(int id) throws DataAccessException{
		return envioRepository.findByConcesionario(id);
	}
}
