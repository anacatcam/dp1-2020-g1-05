package com.springframework.samples.madaja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Transactional
	public void saveVenta(Venta venta) {
		ventaRepository.save(venta);
	}

	@Transactional
	public Venta findVentaByEnvio(int id) throws DataAccessException { //test
		return ventaRepository.findByEnvio(id);
	}
	
	@Transactional(readOnly = true)
    public Page<Venta> getAll(String dni, Pageable pageable) {
        return ventaRepository.findAll(dni, pageable);
    }

}
