package com.springframework.samples.madaja.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;

import com.springframework.samples.madaja.model.Concesionario;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class SearchService {

	private final EntityManager entityManager;
	
	public List<Concesionario> getConcesionarioByPrivincia(String word){
		FullTextEntityManager fullTextEntityManager = 
				Search.getFullTextEntityManager(entityManager);
		
		QueryBuilder qb = fullTextEntityManager
				.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Concesionario.class)
				.get();
		
		Query q = qb.keyword()
				.onFields("nombre","provincia","localidad","pais")
				.matching(word)
				.createQuery();
		
		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(q, Concesionario.class);
		
		return (List<Concesionario>) fullTextQuery.getResultList();
	}
}
