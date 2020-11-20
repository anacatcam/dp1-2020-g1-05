package com.springframework.samples.madaja.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Cambio;
import com.springframework.samples.madaja.model.Combustible;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Disponible;
import com.springframework.samples.madaja.model.SeguroVehiculo;
import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.repository.CambioRepository;
import com.springframework.samples.madaja.repository.CombustibleRepository;
import com.springframework.samples.madaja.repository.ConcesionarioRepository;
import com.springframework.samples.madaja.repository.DisponibleRepository;
import com.springframework.samples.madaja.repository.SeguroVehiculoRepository;
import com.springframework.samples.madaja.repository.VehiculosRepository;

@Service
public class VehiculosService {
	
	private VehiculosRepository vehiculosRepository;	
	private CambioRepository cambioRepository;
	private ConcesionarioRepository concesionarioRepository;
	private CombustibleRepository combustibleRepository;
	private DisponibleRepository disponibleRepository;
	private SeguroVehiculoRepository seguroVehiculoRepository;
		
	public VehiculosService(VehiculosRepository vehiculosRepository, CambioRepository cambioRepository, 
			ConcesionarioRepository concesionarioRepository, CombustibleRepository combustibleRepository,
			DisponibleRepository disponibleRepository, SeguroVehiculoRepository seguroVehiculoRepository) {
		this.vehiculosRepository=vehiculosRepository;
		this.cambioRepository=cambioRepository;
		this.concesionarioRepository=concesionarioRepository;
		this.combustibleRepository=combustibleRepository;
		this.disponibleRepository=disponibleRepository;
		this.seguroVehiculoRepository=seguroVehiculoRepository;
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

//	@Transactional(readOnly = true)
//	public Collection<Vehiculos> findVehiculoByPlazas(int plazas) {
//		return vehiculosRepository.findByPlazas(plazas);
//	}
	
	@Transactional(readOnly = true)
	public Collection<Cambio> findAllCambios() {
		return cambioRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Collection<Concesionario> findAllConcesionarios() {
		return concesionarioRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Collection<Combustible> findAllCombustibles() {
		return combustibleRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Collection<Disponible> findAllDisponibles() {
		return disponibleRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Collection<SeguroVehiculo> findAllSeguros() {
		return seguroVehiculoRepository.findAll();
	}


}
