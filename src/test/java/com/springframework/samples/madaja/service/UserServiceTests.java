package com.springframework.samples.madaja.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.springframework.samples.madaja.model.User;
import com.springframework.samples.madaja.repository.UserRepository;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTests {
	
	@Mock
	private UserRepository userRepository;
	
	@Autowired
	protected UserService userService;
	
	private User usuario;
	
	@BeforeEach	
	void setUp() {
		userService= new UserService(userRepository);
		
		usuario = new User();
		usuario.setUsername("alejandro");
		usuario.setEnabled(Boolean.TRUE);
		usuario.setPassword("contraseña3");
	}
	
	@Test
	void testSaveUser() throws Exception{
		userService.saveUser(usuario);
		verify(userRepository).save(usuario);
	}
	
	@Test
	void testFindUser() throws Exception{
		when(userRepository.findById(anyString())).thenReturn(Optional.of(usuario));
		
		userService.findUser(anyString());
		
		verify(userRepository).findById(anyString());
		assertEquals(Optional.of(usuario),userService.findUser(anyString()));
		
	}
}
