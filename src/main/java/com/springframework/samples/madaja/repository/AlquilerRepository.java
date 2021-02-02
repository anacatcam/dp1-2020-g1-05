package com.springframework.samples.madaja.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Alquiler;

public interface AlquilerRepository extends Repository<Alquiler, Integer>{
	Iterable<Alquiler> findAll() throws DataAccessException; //TODOS los alquileres
	
	@Query("SELECT alquiler FROM Alquiler alquiler WHERE alquiler.id =:id")
	public Alquiler findById(@Param("id") int id); //Alquiler por id
	
	@Query("SELECT alquiler FROM Alquiler alquiler WHERE alquiler.cliente.dni =:dni") //Encontrar alquileres por DNI del cliente
	public List<Alquiler> findByDniCliente(@Param("dni") String dni);
		
	@Query("SELECT alquiler FROM Alquiler alquiler WHERE alquiler.cliente.id = :id AND alquiler.vehiculo.matricula = :matricula")//(H7) Encontrar un alquiler del cliente por matricula de vehiculo
	public Alquiler findAlquilerConcretoCliente(@Param("id") String id, @Param("matricula") String matricula); // se puede borrar pq no se usa

	void save(Alquiler alquiler) throws DataAccessException;
	
	@Query("SELECT alquiler FROM Alquiler alquiler WHERE alquiler.envio.id = :id")
	public Alquiler findByEnvio(@Param("id") int id);
}
