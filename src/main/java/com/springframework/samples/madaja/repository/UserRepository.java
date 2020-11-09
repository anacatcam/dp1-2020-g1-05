package com.springframework.samples.madaja.repository;

import org.springframework.data.repository.CrudRepository;

import com.springframework.samples.madaja.model.User;


public interface UserRepository extends  CrudRepository<User, String>{
	
}
