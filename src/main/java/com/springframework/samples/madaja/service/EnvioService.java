package com.springframework.samples.madaja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Envio;
import com.springframework.samples.madaja.model.EstadoEnvio;
import com.springframework.samples.madaja.repository.EnvioRepository;
import com.springframework.samples.madaja.repository.EstadoEnvioRepository;

@Service
public class EnvioService {
	private EnvioRepository envioRepository;
	private EstadoEnvioRepository estadoEnvioRepository;
	
	@Autowired
	public EnvioService(EnvioRepository envioRepository,EstadoEnvioRepository estadoEnvioRepository) {
		this.envioRepository = envioRepository;
		this.estadoEnvioRepository = estadoEnvioRepository;
	}
	
	@Transactional(readOnly = true)
	public Envio findEnvioById(int id) throws DataAccessException{
		return envioRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Envio> findEnvioAlqByConcesionario(int id) throws DataAccessException{
		return envioRepository.findByConcesionarioAlq(id);
	}

	@Transactional(readOnly = true)
	public List<Envio> findEnvioVentByConcesionario(int id) throws DataAccessException{
		return envioRepository.findByConcesionarioVent(id);
	}
	
	@Transactional(readOnly = true)
	public Iterable<EstadoEnvio> findAllEstados(){
		return this.estadoEnvioRepository.findAll();
	}
	
	@Transactional
	public void saveEnvio(Envio envio) {
		envioRepository.save(envio);
	}
}
