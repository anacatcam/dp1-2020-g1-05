package com.springframework.samples.madaja.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Vehiculos;

public interface VehiculosRepository extends Repository<Vehiculos, Integer>{
	
	void save(Vehiculos vehiculo) throws DataAccessException;
	
	Collection<Vehiculos> findAll() throws DataAccessException;
	
	@Query("SELECT vehiculos FROM Vehiculos vehiculos WHERE vehiculos.id =:id")
	public Vehiculos findById(@Param("id") int id);

//	@Query("SELECT vehiculos FROM Vehiculos vehiculos WHERE vehiculos.plazas =:plazas")
//	Collection<Vehiculos> findByPlazas(@Param("plazas") int plazas);

}
