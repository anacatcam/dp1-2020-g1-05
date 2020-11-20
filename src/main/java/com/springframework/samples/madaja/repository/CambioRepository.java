package com.springframework.samples.madaja.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import com.springframework.samples.madaja.model.Cambio;

public interface CambioRepository extends Repository<Cambio, Integer>{
	
	void save(Cambio cambio) throws DataAccessException;
	
	Collection<Cambio> findAll() throws DataAccessException;

}
