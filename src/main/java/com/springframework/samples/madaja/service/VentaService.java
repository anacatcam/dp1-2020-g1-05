package com.springframework.samples.madaja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Venta;
import com.springframework.samples.madaja.repository.VentaRepository;

@Service
public class VentaService {

	private VentaRepository ventaRepository;
	
	
	@Autowired
	public VentaService(VentaRepository ventaRepository) {
		this.ventaRepository = ventaRepository;
	}
	
	@Transactional(readOnly = true)
	public Venta findVentaById(int id) throws DataAccessException{
		return ventaRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Iterable<Venta> findAllVentas() throws DataAccessException{
		return ventaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Venta> findVentasByDni(String dni) throws DataAccessException{
		return ventaRepository.findByDniCliente(dni);
	} 
}
