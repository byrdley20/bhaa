package com.coptertours.controller.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	Aircraft addAircraft(@RequestBody Aircraft aircraft, final HttpServletResponse response) {
		return this.aircraftRepository.save(aircraft);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteAircraft(@PathVariable Long id) {
		this.aircraftRepository.delete(id);
	}
}
