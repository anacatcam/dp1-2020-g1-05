package com.springframework.samples.madaja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Integer>{
	
	Iterable<Reserva> findAll() throws DataAccessException; //TODAS las reservas
	
	Optional<Reserva> findById(int id) throws DataAccessException; //Reserva por ID
	
	@Query("SELECT reserva FROM Reserva reserva WHERE reserva.cliente.dni =:dni") //Encontrar reservas por DNI del cliente
	public List<Reserva> findByDniReserva(@Param("dni") String dni);

	

	/** Listar reservas con sus alquileres asociados **/ /*
	@Query("SELECT * FROM Reserva r JOIN Alquiler a ON r.id=a.id_reserva")
	public ¿? findResAlq();
	*/
	
	
}
