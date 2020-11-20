package com.springframework.samples.madaja.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import com.springframework.samples.madaja.model.Combustible;

public interface CombustibleRepository extends Repository<Combustible, Integer>{
	
	void save(Combustible combustible) throws DataAccessException;
	
	Collection<Combustible> findAll() throws DataAccessException;

}
