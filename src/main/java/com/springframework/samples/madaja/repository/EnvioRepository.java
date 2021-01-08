package com.springframework.samples.madaja.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Envio;

public interface EnvioRepository extends Repository<Envio, Integer> {

	void save(Envio envio) throws DataAccessException;
		
	@Query("SELECT envio FROM Envio envio WHERE envio.id = :id")
	Envio findById(@Param("id") int id);
	
	@Query("SELECT envio FROM Envio envio WHERE envio.alquiler.vehiculo.concesionario.id = :id") // Envios de alquiler de un concesionario
	List<Envio> findByConcesionarioAlq(@Param("id") int id);
	//nuevo
	@Query("SELECT envio FROM Envio envio WHERE envio.venta.vehiculo.concesionario.id = :id") // Envios de venta de un concesionario
	List<Envio> findByConcesionarioVent(@Param("id") int id);
}
