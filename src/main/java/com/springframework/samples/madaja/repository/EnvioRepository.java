package com.springframework.samples.madaja.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Envio;

public interface EnvioRepository extends Repository<Envio, Integer> {

	Iterable<Envio> findAll() throws DataAccessException; // Todos los envios
	
	@Query("SELECT envio FROM Envio envio WHERE envio.alquiler.vehiculo.concesionario.id = :id") // Envios de un concesionario
	List<Envio> findByConcesionario(@Param("id") int id);
}
