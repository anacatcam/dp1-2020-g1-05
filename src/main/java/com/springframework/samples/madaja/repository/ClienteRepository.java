package com.springframework.samples.madaja.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Cliente;

public interface ClienteRepository extends Repository<Cliente, Integer>{
	
	Cliente findByDni(String id) throws DataAccessException; //Encontrar cliente por ID
	
	Iterable<Cliente> findAll() throws DataAccessException; //TODOS los clientes
	
	void save(Cliente cliente) throws DataAccessException;
	
	@Query("SELECT cliente FROM Cliente cliente WHERE cliente.user.username = :username")
	public Cliente findByUsername(@Param("username") String username);
}
