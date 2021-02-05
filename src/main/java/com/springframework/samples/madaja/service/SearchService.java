package com.springframework.samples.madaja.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.Concesionario;
import com.springframework.samples.madaja.model.Vehiculos;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
public class SearchService {

	private EntityManager entityManager;
	
	@Autowired
	public SearchService(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
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
		
		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(q, Concesionario.class); 
		
		List<Concesionario> concesionarioList = fullTextQuery.getResultList();
		
		return concesionarioList;
	}

	@Transactional
	public List<Cliente> searchClientes(String searchText){
		FullTextEntityManager fullTextEntityManager = 
				Search.getFullTextEntityManager(entityManager);
		
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Cliente.class)
				.overridesForField("firstName", "edgeNGram_query")
				.overridesForField("lastName", "edgeNGram_query")
				.overridesForField("dni", "edgeNGram_query")
				.get();
		
		Query q = qb.keyword()
				.onFields("firstName","lastName","dni")
				.matching(searchText)
				.createQuery();
		
		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(q,Cliente.class);
		
		List<Cliente> clienteList = fullTextQuery.getResultList();
		
		return clienteList;
	}
	
	@Transactional
	public List<Vehiculos> searchVehiculos(String searchText){
		FullTextEntityManager fullTextEntityManager = 
				Search.getFullTextEntityManager(entityManager);
		
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Vehiculos.class)
				.overridesForField("marca", "edgeNGram_query")
				.overridesForField("modelo", "edgeNGram_query")
				.get();
		
		Query q = qb.keyword()
				.onFields("marca","modelo","plazas","puertas")
				.matching(searchText)
				.createQuery();
		
		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(q, Vehiculos.class);
		
		List<Vehiculos> vehiculosList = fullTextQuery.getResultList();
		
		return vehiculosList;
	}
}
