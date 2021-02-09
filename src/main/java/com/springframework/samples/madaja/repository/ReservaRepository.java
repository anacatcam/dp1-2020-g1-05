package com.springframework.samples.madaja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Reserva;

public interface ReservaRepository extends Repository<Reserva, Integer>{
	
	void save(Reserva reserva) throws DataAccessException;
	
	//SUSTITUIDO POR PAGINACIÓN
	Iterable<Reserva> findAll() throws DataAccessException; //TODAS las reservas
	
	Optional<Reserva> findById(int id) throws DataAccessException; //Reserva por ID
	
	@Query("SELECT reserva FROM Reserva reserva WHERE reserva.cliente.dni =:dni") //Encontrar reservas por DNI del cliente
	public List<Reserva> findByDniReserva(@Param("dni") String dni);	
	
	/** Eliminar reservas y venta/alq asociado/a**/
	@Modifying
	@Query(value ="delete FROM venta  where reserva_id=:id", nativeQuery = true)
	void eliminarVentaReserva(@Param("id") int id);
	@Modifying
	@Query(value ="delete FROM alquiler  where reserva_id=:id", nativeQuery = true)
	void eliminarAlquilerReserva(@Param("id") int id);
	@Modifying
	@Query(value ="delete FROM reserva  where id=:id", nativeQuery = true)
	void eliminarReserva(@Param("id") int id);
	
	Page<Reserva> findAll(Pageable pageable);
}
