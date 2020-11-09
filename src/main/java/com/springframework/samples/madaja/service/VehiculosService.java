package com.springframework.samples.madaja.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.repository.VehiculosRepository;

@Service
public class VehiculosService {
	
	private VehiculosRepository vehiculosRepository;
	
	public VehiculosService(VehiculosRepository vehiculosRepository) {
		this.vehiculosRepository=vehiculosRepository;
	}
	
	@Transactional(readOnly = true)
	public Collection<Vehiculos> findAllVehiculos() throws DataAccessException {
		return vehiculosRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Vehiculos findVehiculoById(int id) throws DataAccessException {
		return vehiculosRepository.findById(id);
	}
	
	@Transactional
	public void saveVehiculo(Vehiculos vehiculo) {
		vehiculosRepository.save(vehiculo);
	}


}
