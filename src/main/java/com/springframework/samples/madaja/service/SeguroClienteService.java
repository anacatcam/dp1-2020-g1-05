
package com.springframework.samples.madaja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.SeguroCliente;
import com.springframework.samples.madaja.repository.SeguroClienteRepository;

@Service
public class SeguroClienteService {
	
	private SeguroClienteRepository seguroClienteRepository;
	
	@Autowired
	public SeguroClienteService(SeguroClienteRepository seguroClienteRepository) {
		this.seguroClienteRepository=seguroClienteRepository;
	}
	
	@Transactional
	public void saveSeguroCliente(SeguroCliente seguroCliente)  throws DataAccessException{
		seguroClienteRepository.save(seguroCliente);
	}
	
	@Transactional(readOnly = true)
	public SeguroCliente findSeguroClienteById(int id) throws DataAccessException {
		return seguroClienteRepository.findById(id);
	}

	@Transactional
	@Modifying
	public void deleteById(int id) {
		seguroClienteRepository.deleteById(id);
	}
}
