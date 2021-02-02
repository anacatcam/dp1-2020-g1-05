package com.springframework.samples.madaja.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.SeguroCliente;


public interface SeguroClienteRepository extends Repository<SeguroCliente, Integer>{
	
	void save(SeguroCliente seguroCliente) throws DataAccessException;
	
	@Query("SELECT seguroCliente FROM SeguroCliente seguroCliente WHERE seguroCliente.id =:id")
	public SeguroCliente findById(@Param("id") int id);
	
	@Modifying
	@Query(value ="delete FROM seguro_cliente  where id=:id", nativeQuery = true)
	void deleteById(@Param("id") int id);

}
