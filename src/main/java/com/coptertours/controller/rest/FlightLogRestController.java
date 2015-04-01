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

import com.coptertours.domain.FlightLog;
import com.coptertours.repository.FlightLogRepository;

@RestController
@RequestMapping(value = "/flightLogs")
public class FlightLogRestController {
	@Autowired
	FlightLogRepository flightLogRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<FlightLog> flightLogs() {
		return this.flightLogRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	FlightLog addFlightLog(@RequestBody FlightLog flightLog, final HttpServletResponse response) {
		return this.flightLogRepository.save(flightLog);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteFlightLog(@PathVariable Long id) {
		this.flightLogRepository.delete(id);
	}
}
