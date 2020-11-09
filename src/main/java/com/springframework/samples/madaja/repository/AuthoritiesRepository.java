package com.springframework.samples.madaja.repository;

import org.springframework.data.repository.CrudRepository;

import com.springframework.samples.madaja.model.Authorities;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	
}
