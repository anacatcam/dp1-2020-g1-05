package com.springframework.samples.madaja.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/","/oups").permitAll()
				.antMatchers(HttpMethod.POST, "/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/**").permitAll()
				.antMatchers("/users/new").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("admin")
				.antMatchers("/api/v1/**").permitAll()
				.antMatchers("/alquileresAPI/**").hasAnyAuthority("cliente")
				.antMatchers("/ofertaAPI/**", "/concesionariosAPI/**").authenticated()				
				.antMatchers("/concesionario").permitAll()
				.antMatchers("/concesionario/*").permitAll()
				.antMatchers("/concesionario/**/EnviosAlquileres").hasAnyAuthority("admin")
				.antMatchers("/concesionario/**/EnviosVentas").hasAnyAuthority("admin")
				.antMatchers("/concesionario/**/envio=**/edit").hasAnyAuthority("admin")
				.antMatchers("/vehiculos").authenticated()
				.antMatchers("/vehiculos/*").hasAnyAuthority("cliente", "admin")
				.antMatchers("/vehiculos/disponible/1", "/vehiculos/disponible/2", "/vehiculos/disponible/3").authenticated()
				.antMatchers("/vehiculos/disponible/7", "/vehiculos/disponible/6", "/vehiculos/disponible/5",
						"/vehiculos/disponible/4").hasAnyAuthority("admin")
				.antMatchers("/vehiculos/**/comprar").hasAnyAuthority("cliente", "admin")
				.antMatchers("/vehiculos/**/alquilar").hasAnyAuthority("cliente", "admin")
				.antMatchers("/vehiculos/new").hasAnyAuthority("admin")
				.antMatchers("/vehiculos/**/edit").hasAnyAuthority("admin")
				.antMatchers("/vehiculos/**/delete").hasAnyAuthority("admin")
				.antMatchers("/vehiculos/**/incidencia/new").hasAnyAuthority("admin")
				.antMatchers("/vehiculos/**/incidencia/**/edit").hasAnyAuthority("admin")
				.antMatchers("/vehiculos/**/seguroCliente/new").hasAnyAuthority("admin")
				.antMatchers("/vehiculos/**/seguroCliente/**/edit").hasAnyAuthority("admin")
				.antMatchers("/vehiculos/**/seguroCliente/**/delete").hasAnyAuthority("admin")
				.antMatchers("/oferta", "/oferta/*").authenticated()
				.antMatchers("/oferta/new", "/oferta/**/edit", "/oferta/**/delete").hasAnyAuthority("admin")
				.antMatchers("/reservas").hasAnyAuthority("admin")
				.antMatchers("/reservas/mis-reservas").hasAnyAuthority("cliente", "admin")
				.antMatchers("/reservas/**/reservar/**").hasAnyAuthority("cliente", "admin")
				.antMatchers("/reservas/*/delete").hasAnyAuthority("cliente", "admin")
				.antMatchers("/alquileres").hasAnyAuthority("admin")
				.antMatchers("/alquileres/**/devolucion").hasAnyAuthority("admin")
				.antMatchers("/ventas").hasAnyAuthority("admin")
				.antMatchers("/vehiculos/**/seguro/**/view").hasAnyAuthority("cliente", "admin")
				.antMatchers("/clientes/**").hasAnyAuthority("admin")
				.antMatchers("/MisAlquileres").hasAnyAuthority("cliente")
				.antMatchers("/MisVentas").hasAnyAuthority("cliente")
				.antMatchers("/recogidas").hasAnyAuthority("admin")
				.antMatchers("/recogida/**").hasAnyAuthority("admin")
				.anyRequest().denyAll()
				.and()
				 	.formLogin()
				 	/*.loginPage("/login")*/
				 	.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/"); 
                // Configuración para que funcione la consola de administración 
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.csrf().ignoringAntMatchers("/api/**");
                http.headers().frameOptions().sameOrigin();
                //PARA LIMITAR A UNA SESIÓN ACTIVA A LA VEZ
                http.sessionManagement().maximumSessions(1)
                .expiredUrl("/login?expired").and()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	      	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		PasswordEncoder encoder =  NoOpPasswordEncoder.getInstance();
	    return encoder;
	}
	
}


