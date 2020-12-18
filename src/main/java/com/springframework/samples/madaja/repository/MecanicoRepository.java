package com.springframework.samples.madaja.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Mecanico;

public interface MecanicoRepository extends Repository<Mecanico, Integer>{
	
	void save(Mecanico mecanico) throws DataAccessException;
	
	@Query("SELECT mecanico FROM Mecanico mecanico WHERE mecanico.dni =:dni")
	public Mecanico findById(@Param("dni") int dni);
	
	Collection<Mecanico> findAll();

}
