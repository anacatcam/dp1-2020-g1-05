package com.springframework.samples.madaja.repository;


import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Concesionario;

public interface ConcesionarioRepository extends Repository<Concesionario, Integer>{
	
void save(Concesionario concesionario) throws DataAccessException;

	/////////
	Page<Concesionario> findAll(Pageable pageable);
	/////////
	
	/** anterior a la paginacion **/
	Collection<Concesionario> findAll() throws DataAccessException;
	
	@Query("SELECT concesionario FROM Concesionario concesionario WHERE concesionario.id =:id")
	public Concesionario findById(@Param("id") int id);
	

}
