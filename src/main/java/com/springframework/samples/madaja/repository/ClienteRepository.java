package com.springframework.samples.madaja.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import com.springframework.samples.madaja.model.Cliente;

public interface ClienteRepository extends Repository<Cliente, Integer>{
	
	Cliente findByDni(String id) throws DataAccessException; //Encontrar cliente por ID
	
	Iterable<Cliente> findAll() throws DataAccessException; //TODOS los clientes
	
	void save(Cliente cliente) throws DataAccessException;
}
