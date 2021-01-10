package com.springframework.samples.madaja.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Mecanico;

public interface MecanicoRepository extends Repository<Mecanico, Integer>{
	
	void save(Mecanico mecanico) throws DataAccessException;
	
	@Query("SELECT mecanico FROM Mecanico mecanico WHERE mecanico.dni =:dni")
	public Mecanico findById(@Param("dni") String dni);
	
	Collection<Mecanico> findAll() throws DataAccessException;
	
	@Query("SELECT mecanico FROM Mecanico mecanico left join fetch mecanico.incidencias incidencia WHERE incidencia.id =:id")
	public List<Mecanico> findMecanicosByIncidencia(@Param("id") int id);
	

	
}
