package com.springframework.samples.madaja.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Oferta;
import com.springframework.samples.madaja.repository.OfertaRepository;

@Service
public class OfertaService {
	
	private OfertaRepository ofertaRepository;
	
	public OfertaService(OfertaRepository ofertaRepository) {
		this.ofertaRepository=ofertaRepository;
	}
	
	@Transactional(readOnly = true)
	public Collection<Oferta> findAllOfertas() throws DataAccessException {
		return ofertaRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Oferta findOfertaById(int id) throws DataAccessException {
		return ofertaRepository.findById(id);
	}
	
	@Transactional
	public void saveOferta(Oferta oferta) {
		ofertaRepository.save(oferta);
	}
	
	@Transactional
	@Modifying
	public void deleteById(int id) {
		ofertaRepository.deleteById(id);
	}

}
