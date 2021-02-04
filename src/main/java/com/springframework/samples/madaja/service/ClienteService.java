package com.springframework.samples.madaja.service;

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

import com.springframework.samples.madaja.model.Cliente;
import com.springframework.samples.madaja.model.User;
import com.springframework.samples.madaja.repository.ClienteRepository;

@Service
public class ClienteService {
	private ClienteRepository clienteRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthoritiesService authoritiesService;
	private EntityManager entityManager;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository,EntityManager entityManager) {
		this.clienteRepository = clienteRepository;
		this.entityManager = entityManager;
	}
	
	@Transactional(readOnly = true)
	public Cliente findClienteByDni(String id) throws DataAccessException {
		return clienteRepository.findByDni(id);
	}
	
	@Transactional(readOnly = true)
	public Iterable<Cliente> findAllClientes() throws DataAccessException{
		return clienteRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Page<Cliente> getAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	
	@Transactional
	public void saveCliente(Cliente cliente) throws DataAccessException {
		//creating cliente
		clienteRepository.save(cliente);	
		
		//creating user
		userService.saveUser(cliente.getUser());
		
		//creating authorities
		authoritiesService.saveAuthorities(cliente.getUser().getUsername(), "cliente");
	}
	
	@Transactional
	public Cliente findClienteByUsername(String username) {
		return clienteRepository.findByUsername(username);
	}
	
	@Transactional
	public Cliente findClienteById(Integer clienteId) {
		return clienteRepository.findById(clienteId);
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
 }
