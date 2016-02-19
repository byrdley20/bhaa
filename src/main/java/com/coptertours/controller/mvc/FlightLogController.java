package com.coptertours.controller.mvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.coptertours.repository.UserRepository;
import com.coptertours.util.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

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
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/flightLog.html")
	String flightLog(Model model,
			@RequestParam Long id,
			@RequestParam(required = false) Integer month,
			@RequestParam(required = false) Integer year,
			@RequestParam(required = false) String action,
			@RequestParam(required = false) String printFormAutofill) throws JsonProcessingException {
		Aircraft aircraft = this.aircraftRepository.findOne(id);

		List<AdCompliance> adCompliances = this.adComplianceRepository.findByModelAndAircraftAndDailyAndActiveTrue(aircraft.getModel().getId(), aircraft.getId(), true);

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

		Map<Date, FlightLog> dateToFlightLog = new HashMap<Date, FlightLog>();
		Map<String, BigDecimal> locationHoursMap = new HashMap<String, BigDecimal>();
		BigDecimal monthlyHobbsTotal = BigDecimal.ZERO;
		for (FlightLog flightLog : flightLogs) {
			if (AppConstants.PRINT.equals(action) && Boolean.parseBoolean(printFormAutofill)) {
				dateToFlightLog.put(flightLog.getDate(), flightLog);
			}
			BigDecimal flightLogTotal = flightLog.getHobbsEnd().subtract(flightLog.getHobbsBegin());
			monthlyHobbsTotal = monthlyHobbsTotal.add(flightLogTotal);
			String locationName = flightLog.getLocation().getName();
			BigDecimal locationHours = locationHoursMap.get(locationName);
			if (locationHours == null) {
				locationHoursMap.put(locationName, flightLogTotal);
			} else {
				locationHoursMap.put(locationName, locationHours.add(flightLogTotal));
			}
		}

		if (AppConstants.PRINT.equals(action)) {
			if (Boolean.parseBoolean(printFormAutofill)) {
				Calendar autoFillStartDate = DateUtil.findMonthStartDate(month, year);
				BigDecimal latestHobbs = null;
				while (!autoFillStartDate.getTime().after(endDateCal.getTime())) {
					Date date = autoFillStartDate.getTime();
					FlightLog dateFlightLog = dateToFlightLog.get(date);
					if (dateFlightLog == null) {
						autoFillFlightLog(aircraft, date, latestHobbs);
					} else {
						latestHobbs = dateFlightLog.getHobbsEnd();
					}
					autoFillStartDate.add(Calendar.DAY_OF_MONTH, 1);
				}
				flightLogs = this.flightLogRepository.findByAircraftAndDateBetween(aircraft, startDateCal.getTime(), endDateCal.getTime(), sortByDate());
			}
			model.addAttribute("printMonth", true);
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
		model.addAttribute("locationHours", locationHoursMap);
		return "flightLog";
	}

	private void autoFillFlightLog(Aircraft aircraft, Date date, BigDecimal latestHobbs) {
		if (latestHobbs == null) {
			latestHobbs = this.flightLogRepository.findMostRecentHobbsEndBeforeDateByAircraft(aircraft, date);
			if (latestHobbs == null) {
				latestHobbs = BigDecimal.ZERO;
			}
		}
		FlightLog flightLog = new FlightLog();
		flightLog.setAircraft(aircraft);
		flightLog.setDate(date);
		flightLog.setHobbsBegin(latestHobbs);
		flightLog.setHobbsEnd(latestHobbs);
		flightLog.setLocation(this.locationRepository.findByName(AppConstants.AUTO_LOCATION_NAME).get(0));
		flightLog.setOperation(this.operationRepository.findByName(AppConstants.AUTO_OPERATION_NAME).get(0));
		flightLog.setStarts(0);
		flightLog.setUser(this.userRepository.findByUserId(AppConstants.AUTO_USER_ID).get(0));
		System.out.println("creating:" + new Gson().toJson(flightLog));
		this.flightLogRepository.save(flightLog);
	}
}
