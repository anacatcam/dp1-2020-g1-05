package com.springframework.samples.madaja.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.springframework.samples.madaja.model.Reserva;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ReservaServiceTests {

	@Autowired
	protected ReservaService reservaService;

	@Test
	void shouldFindReservaById() {
		Optional<Reserva> reserva = this.reservaService.findReservaById(1);
		assertThat(reserva.get().getCliente().getDni()).isEqualTo("15442604");
	}

	@Test
	void shouldFinAllReserva() {
		Iterable<Reserva> reservas = this.reservaService.findAllReserva();
		Iterator<Reserva> reserva_it = reservas.iterator();
		Boolean encontrado = false;
		while (reserva_it.hasNext()) {
			Reserva reserva = reserva_it.next();
			if (reserva.getId() == 1) {
				encontrado = true;
				break;
			}
		}
		assertThat(encontrado).isTrue();
	}

	@Test
	void shouldFindReservaByDni() {
		List<Reserva> reservas = this.reservaService.findByDNI("15442604");
		assertThat(reservas.size()).isGreaterThan(0);
	}

	@Test
	void shouldDeleteReserva() {

		this.reservaService.deleteRes(1);

		Iterable<Reserva> reservas = this.reservaService.findAllReserva();
		Iterator<Reserva> reserva_it = reservas.iterator();
		Boolean encontrado = false;
		while (reserva_it.hasNext()) {
			Reserva reserva = reserva_it.next();
			if (reserva.getId() == 1) {
				encontrado = true;
				break;
			}
		}
		assertThat(encontrado).isFalse();

	}

}