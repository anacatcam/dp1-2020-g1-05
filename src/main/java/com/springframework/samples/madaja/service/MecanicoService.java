package com.springframework.samples.madaja.service;


import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Mecanico;
import com.springframework.samples.madaja.repository.MecanicoRepository;

@Service
public class MecanicoService {

	private MecanicoRepository mecanicoRepository;
	
	@Autowired
	public MecanicoService(MecanicoRepository mecanicoRepository) {
		this.mecanicoRepository = mecanicoRepository;
	}
	
	@Transactional(readOnly = true)
	public Mecanico findById(String dni) throws DataAccessException {
		return mecanicoRepository.findById(dni);
	}
	
	@Transactional(readOnly = true)
	public List<Mecanico> findAll() throws DataAccessException{
		return mecanicoRepository.findAll().stream().collect(Collectors.toList());
	}
	
	public void saveMecanico(Mecanico mecanico) throws DataAccessException{
		 mecanicoRepository.save(mecanico);
	}
	
	@Transactional(readOnly = true)
	public List<Mecanico> findMecanicosByIncidencia(int id){
		return mecanicoRepository.findMecanicosByIncidencia(id);
	}
}
