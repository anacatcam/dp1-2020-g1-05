package com.springframework.samples.madaja.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import com.springframework.samples.madaja.model.Disponible;

public interface DisponibleRepository extends Repository<Disponible, Integer>{
	
	void save(Disponible disponible) throws DataAccessException;
	
	Collection<Disponible> findAll() throws DataAccessException;

}
