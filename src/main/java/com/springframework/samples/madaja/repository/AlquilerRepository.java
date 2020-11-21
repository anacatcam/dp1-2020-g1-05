package com.springframework.samples.madaja.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Alquiler;
import com.springframework.samples.madaja.model.Venta;

public interface AlquilerRepository extends Repository<Alquiler, Integer>{
	Iterable<Alquiler> findAll() throws DataAccessException; //TODOS los alquileres
	
	Alquiler findById(int id) throws DataAccessException; //Alquiler por id
	
	@Query("SELECT alquiler FROM Alquiler alquiler WHERE alquiler.cliente.dni =:dni") //Encontrar ventas por DNI del cliente
	public List<Alquiler> findByDniCliente(@Param("dni") int dni);
	
	@Query("SELECT alquiler FROM Alquiler alquiler WHERE alquiler.cliente.id =:id") //(H7) Encontrar todos los alquileres de un cliente
	public List<Alquiler> findAlquileresCliente(@Param("id") int id);
	
	@Query("SELECT alquiler FROM Alquiler alquiler WHERE alquiler.cliente.id = :id AND alquiler.vehiculo.matricula = :matricula")//(H7) Encontrar un alquiler del cliente por matricula de vehiculo
	public Alquiler findAlquilerConcretoCliente(@Param("id") int id, @Param("matricula") String matricula);
}
