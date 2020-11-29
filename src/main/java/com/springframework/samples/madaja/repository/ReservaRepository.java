package com.springframework.samples.madaja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springframework.samples.madaja.model.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Integer>{
	
	Iterable<Reserva> findAll() throws DataAccessException; //TODAS las reservas
	
	Optional<Reserva> findById(int id) throws DataAccessException; //Reserva por ID
	
	@Query("SELECT reserva FROM Reserva reserva WHERE reserva.cliente.dni =:dni") //Encontrar reservas por DNI del cliente
	public List<Reserva> findByDniReserva(@Param("dni") String dni);	

//	/** Eliminar reservas **/
//	@Modifying
//	@Query(value ="UPDATE venta SET reserva_id = null WHERE reserva_id =:id", nativeQuery = true)
//	void actVenta(@Param("id") int id);
//	@Modifying
//	@Query(value ="UPDATE alquiler SET reserva_id = null WHERE reserva_id =:id", nativeQuery = true)
//	void actAlquiler(@Param("id") int id);
//	@Modifying
//	@Query(value ="delete FROM RESERVA  where id=:id", nativeQuery = true)
//	void eliminarReserva(@Param("id") int id);
	
	
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
	
}
