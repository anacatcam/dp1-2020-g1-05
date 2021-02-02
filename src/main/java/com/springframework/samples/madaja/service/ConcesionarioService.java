package com.springframework.samples.madaja.service;


import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.repository.ConcesionarioRepository;

@Service
public class ConcesionarioService {
	
	private ConcesionarioRepository concesionarioRepository;
	private EntityManager entityManager;
	
	@Autowired
	public ConcesionarioService(ConcesionarioRepository concesionarioRepository,EntityManager entityManager) {
		this.concesionarioRepository=concesionarioRepository;
		this.entityManager=entityManager;
	}
	
	@Transactional(readOnly = true)
	public Page<Concesionario> getAll(Pageable pageable) {
		return concesionarioRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Collection<Concesionario> findAllConcesionarios() throws DataAccessException {
		return concesionarioRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Concesionario findConcesionarioById(int id) throws DataAccessException {
		return concesionarioRepository.findById(id);
	}
	
	@Transactional
	public void saveConcesionario(Concesionario concesionario) {
		concesionarioRepository.save(concesionario);
	}

	@Transactional
	public List<Concesionario> searchConcesionarios(String searchText){
		FullTextEntityManager fullTextEntityManager = 
				Search.getFullTextEntityManager(entityManager);
		
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Concesionario.class)
				.overridesForField("nombre", "edgeNGram_query")
				.overridesForField("provincia", "edgeNGram_query")
				.overridesForField("localidad", "edgeNGram_query")
				.overridesForField("pais", "edgeNGram_query")
				.get(); 
		
		Query q = qb.keyword()
				.onFields("nombre","provincia","localidad","pais")
				.matching(searchText)
				.createQuery();
//		Query q = qb.bool().must(qb.keyword()
//				.onFields("nombre","provincia","localidad","pais")
//				.matching(searchText).createQuery()).createQuery();
		
		
		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(q, Concesionario.class); 
		
		List<Concesionario> concesionarioList = fullTextQuery.getResultList();
		
		return concesionarioList;
	}

}
