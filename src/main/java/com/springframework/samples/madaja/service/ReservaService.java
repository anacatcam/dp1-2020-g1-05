package com.springframework.samples.madaja.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Reserva;
import com.springframework.samples.madaja.repository.ReservaRepository;

@Service
public class ReservaService {
	private ReservaRepository reservaRepository;
	
	@Autowired
	public ReservaService(ReservaRepository reservaRepository) {
		this.reservaRepository = reservaRepository;
	}
	
	@Transactional(readOnly = true)
	public Reserva findReservaById(int id) throws DataAccessException{
		return reservaRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Iterable<Reserva> findAllReserva() throws DataAccessException{
		return reservaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Reserva> findByDNI(String dni) throws DataAccessException{
		return reservaRepository.findByDniReserva(dni);
	}
}
