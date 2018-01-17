package com.coptertours.controller.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.Reservation;
import com.coptertours.repository.ReservationRepository;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationRestController {
	@Autowired
	ReservationRepository reservationRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Reservation> reservations() {
		return this.reservationRepository.findAll();
	}
}
