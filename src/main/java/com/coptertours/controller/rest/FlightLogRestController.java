package com.coptertours.controller.rest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.Aircraft;
import com.coptertours.domain.FlightLog;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.FlightLogRepository;
import com.coptertours.repository.UserRepository;
import com.coptertours.util.DateUtil;

@RestController
@RequestMapping(value = "/flightLogs")
public class FlightLogRestController extends BaseRestController {
	@Autowired
	private FlightLogRepository flightLogRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AircraftRepository aircraftRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<FlightLog> flightLogs() {
		return this.flightLogRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	FlightLog addFlightLog(@RequestBody FlightLog flightLog, final HttpServletResponse response) {
		this.resetRole(flightLog.getUser());
		if (flightLog.getUser().getRole() == null) {
			flightLog.setUser(this.userRepository.findOne(flightLog.getUser().getId()));
		}
		return this.flightLogRepository.save(flightLog);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteFlightLog(@PathVariable Long id) {
		this.flightLogRepository.delete(id);
	}

	@RequestMapping(value = "/mostRecentHobbsEnd/{aircraftId}", method = RequestMethod.GET)
	@ResponseBody
	public BigDecimal findMostRecentHobbsEndByAircraft(@PathVariable(value = "aircraftId") Long aircraftId) {
		Aircraft aircraft = new Aircraft();
		aircraft.setId(aircraftId);
		return this.flightLogRepository.findMostRecentHobbsEndByAircraft(aircraft);
	}

	@RequestMapping(value = "/yearlyStarts/{aircraftId}", method = RequestMethod.GET)
	int totalStartsByYear(@PathVariable Long aircraftId) {
		int totalStarts = 0;
		Aircraft aircraft = this.aircraftRepository.findOne(aircraftId);
		if (aircraft != null) {
			Date today = new Date();
			totalStarts = this.flightLogRepository.findTotalStartsByAircraftAndDateBetween(aircraft, DateUtil.findYearStartDate(today), DateUtil.findYearEndDate(today));
		}

		return totalStarts;
	}
}
