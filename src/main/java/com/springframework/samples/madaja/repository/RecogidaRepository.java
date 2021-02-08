package com.springframework.samples.madaja.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Recogida;

public interface RecogidaRepository extends Repository<Recogida, Integer> {

	Iterable<Recogida> findAll() throws DataAccessException;

	void save(Recogida recogida) throws DataAccessException;

	@Query("SELECT recogida FROM Recogida recogida WHERE recogida.id = :id")
	Recogida findById(@Param("id") int id);

}
