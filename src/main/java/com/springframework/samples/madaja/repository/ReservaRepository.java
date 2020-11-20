package com.springframework.samples.madaja.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Reserva;

public interface ReservaRepository extends Repository<Reserva, Integer>{
	
	Iterable<Reserva> findAll() throws DataAccessException; //TODAS las reservas
	
	Reserva findById(int id) throws DataAccessException; //Reserva por ID
	
	@Query("SELECT reserva FROM Reserva reserva WHERE reserva.cliente.dni =:dni") //Encontrar reservas por DNI del cliente
	public List<Reserva> findByDniReserva(@Param("dni") int dni);
}
