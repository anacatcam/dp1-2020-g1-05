package com.springframework.samples.madaja.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Disponible;

public interface DisponibleRepository extends Repository<Disponible, Integer>{
	
	void save(Disponible disponible) throws DataAccessException;
	
	Collection<Disponible> findAll() throws DataAccessException;
	
	@Query("SELECT disponible FROM Disponible disponible WHERE disponible.id =:id")
	public Disponible findById(@Param("id") int id);
	
	//PAGINACIÓN
	Page<Disponible> findAllPagDisponible(Pageable pageable);

}
