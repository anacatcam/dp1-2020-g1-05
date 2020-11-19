package com.springframework.samples.madaja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.repository.AlquilerRepository;

@Service
public class AlquilerService {
	
	private AlquilerRepository alquilerRepository;
	
	@Autowired
	public AlquilerService(AlquilerRepository alquilerRepository) {
		this.alquilerRepository = alquilerRepository;
	}
	
	@Transactional(readOnly = true)
	public Alquiler findAlquilerById(int id) throws DataAccessException{
		return alquilerRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Iterable<Alquiler> findAllAlquiler() throws DataAccessException{
		return alquilerRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Alquiler> findAlquilerByDni(int dni) throws DataAccessException{
		return alquilerRepository.findByDniCliente(dni);
	}
}
