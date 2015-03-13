package com.coptertours.controller.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.Aircraft;
import com.coptertours.repository.AircraftRepository;

@RestController
@RequestMapping(value = "/aircrafts")
public class AircraftRestController {
	@Autowired
	AircraftRepository aircraftRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Aircraft> aircrafts() {
		return this.aircraftRepository.findAll();
	}
}
