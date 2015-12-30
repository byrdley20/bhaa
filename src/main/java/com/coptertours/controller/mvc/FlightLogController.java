package com.coptertours.controller.mvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coptertours.common.AppConstants;
import com.coptertours.domain.AdCompliance;
import com.coptertours.domain.Aircraft;
import com.coptertours.domain.FlightLog;
import com.coptertours.options.Role;
import com.coptertours.repository.AdComplianceRepository;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.FlightLogRepository;
import com.coptertours.repository.LocationRepository;
import com.coptertours.repository.OperationRepository;
import com.coptertours.util.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class FlightLogController extends BaseController {

	@Autowired
	private AircraftRepository aircraftRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private FlightLogRepository flightLogRepository;
	@Autowired
	private AdComplianceRepository adComplianceRepository;

	@RequestMapping("/flightLog.html")
	String flightLog(Model model,
			@RequestParam Long id,
			@RequestParam(required = false) Integer month,
			@RequestParam(required = false) Integer year) throws JsonProcessingException {
		Aircraft aircraft = this.aircraftRepository.findOne(id);

		List<AdCompliance> adCompliances = this.adComplianceRepository.findByModelAndDailyAndActiveTrue(aircraft.getModel(), true, sortByNameAsc());

		Calendar startDateCal = DateUtil.findMonthStartDate(month, year);
		Calendar endDateCal = DateUtil.findMonthEndDate(month, year);
		Date today = new Date();
		Date yearStart = DateUtil.findYearStartDate(today, year);
		Date yearEnd = DateUtil.findYearEndDate(today, year);

		Integer currentYear = endDateCal.get(Calendar.YEAR);

		List<Integer> allYears = new ArrayList<Integer>();
		for (int i = AppConstants.BEGIN_YEAR; i <= DateUtil.getYear(today); i++) {
			allYears.add(i);
		}

		List<FlightLog> flightLogs = this.flightLogRepository.findByAircraftAndDateBetween(aircraft, startDateCal.getTime(), endDateCal.getTime(), sortByDate());

		BigDecimal monthlyHobbsTotal = BigDecimal.ZERO;
		for (FlightLog flightLog : flightLogs) {
			monthlyHobbsTotal = monthlyHobbsTotal.add(flightLog.getHobbsEnd().subtract(flightLog.getHobbsBegin()));
		}

		if (aircraft.getModel().getShowStarts()) {
			model.addAttribute("yearlyStarts", this.flightLogRepository.findTotalStartsByAircraftAndDateBetween(aircraft, yearStart, yearEnd));
		}
		model.addAttribute("aircraft", aircraft);
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("aircraftJSON", mapper.writeValueAsString(aircraft));
		model.addAttribute("monthlyHobbsTotal", monthlyHobbsTotal);
		model.addAttribute("flightLogs", flightLogs);
		model.addAttribute("allUsers", findUsersByRole(Role.PILOT));
		model.addAttribute("allLocations", this.locationRepository.findAllByActiveTrue(sortByNameAsc()));
		model.addAttribute("allOperations", this.operationRepository.findAllByActiveTrue(sortByNameAsc()));
		model.addAttribute("month", startDateCal.get(Calendar.MONTH));
		model.addAttribute("mostRecentHobbsEnd", this.flightLogRepository.findMostRecentHobbsEndByAircraft(aircraft));
		model.addAttribute("hasDailyAd", !CollectionUtils.isEmpty(adCompliances));
		model.addAttribute("allYears", allYears);
		model.addAttribute("currentYear", currentYear);
		return "flightLog";
	}
}
