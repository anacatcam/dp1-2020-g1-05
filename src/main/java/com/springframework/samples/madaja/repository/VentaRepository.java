package com.springframework.samples.madaja.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Vehiculos;
import com.springframework.samples.madaja.model.Venta;

public interface VentaRepository extends Repository<Venta, Integer>{
	
	Iterable<Venta> findAll() throws DataAccessException; //TODAS las ventas
	
	Venta findById(int id) throws DataAccessException; //Venta por ID
	
	@Query("SELECT venta FROM Venta venta WHERE venta.cliente.dni =:dni") //Encontrar ventas por DNI del cliente
	public List<Venta> findByDniCliente(@Param("dni") String dni);
	
}
