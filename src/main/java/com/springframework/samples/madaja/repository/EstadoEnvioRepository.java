package com.springframework.samples.madaja.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import com.springframework.samples.madaja.model.EstadoEnvio;

public interface EstadoEnvioRepository extends Repository<EstadoEnvio,Integer>{

	void save(EstadoEnvio estado) throws DataAccessException;
	Iterable<EstadoEnvio> findAll() throws DataAccessException;
}
