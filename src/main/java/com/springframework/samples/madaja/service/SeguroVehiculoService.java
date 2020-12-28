package com.springframework.samples.madaja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.repository.SeguroVehiculoRepository;

@Service
public class SeguroVehiculoService {
	
	private SeguroVehiculoRepository seguroVehiculoRepository;
	
	@Autowired
	public SeguroVehiculoService(SeguroVehiculoRepository seguroVehiculoRepository) {
		this.seguroVehiculoRepository=seguroVehiculoRepository;
	}
	
	@Transactional
	public void deleteAllSeguros() throws DataAccessException {
		this.seguroVehiculoRepository.deleteAll();
	}
	@Transactional(readOnly = true)
	public SeguroVehiculo findSeguroVehiculoById(int id) throws DataAccessException {
		return seguroVehiculoRepository.findById(id);
	}

}
