package com.springframework.samples.madaja.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.SeguroVehiculo;

public interface SeguroVehiculoRepository extends Repository<SeguroVehiculo, Integer>{
	
	void save(SeguroVehiculo seguroVehiculo) throws DataAccessException;
	
	void deleteAll() throws DataAccessException;
	
	Collection<SeguroVehiculo> findAll() throws DataAccessException;
	
	@Query("SELECT seguroVehiculo FROM SeguroVehiculo seguroVehiculo WHERE seguroVehiculo.id =:id")
	public SeguroVehiculo findById(@Param("id") int id);

}
