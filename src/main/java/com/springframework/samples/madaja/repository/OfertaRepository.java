package com.springframework.samples.madaja.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Oferta;

public interface OfertaRepository extends Repository<Oferta, Integer>{
	
	void save(Oferta oferta) throws DataAccessException;
	
	Collection<Oferta> findAll() throws DataAccessException;

	@Query("SELECT oferta FROM Oferta oferta WHERE oferta.id =:id")	
	public Oferta findById(@Param("id") int id);

}
