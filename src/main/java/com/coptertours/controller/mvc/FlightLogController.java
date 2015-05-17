package com.coptertours.controller.mvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coptertours.domain.Aircraft;
import com.coptertours.domain.FlightLog;
import com.coptertours.domain.User;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.FlightLogRepository;
import com.coptertours.repository.LocationRepository;
import com.coptertours.repository.OperationRepository;
import com.coptertours.repository.UserRepository;
import com.coptertours.util.DateUtil;

@Controller
public class FlightLogController extends BaseController {

	@Autowired
	AircraftRepository aircraftRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	OperationRepository operationRepository;
	@Autowired
	FlightLogRepository flightLogRepository;

	@RequestMapping("/flightLog.html")
	String flightLog(Model model, @RequestParam Long id, @RequestParam(required = false) Integer month) {
		Aircraft aircraft = this.aircraftRepository.findOne(id);
		model.addAttribute("aircraft", aircraft);

		Calendar startDateCal = DateUtil.findMonthStartDate(month);
		Calendar endDateCal = DateUtil.findMonthEndDate(month);

		List<FlightLog> flightLogs = this.flightLogRepository.findByAircraftAndDateBetween(aircraft, startDateCal.getTime(), endDateCal.getTime(), sortByDate());
		model.addAttribute("flightLogs", flightLogs);
		List<User> allUsers = this.userRepository.findAll(sortByLastNameAsc());
		List<User> clonedAllUsers = new ArrayList<User>(allUsers.size());
		for (User user : allUsers) {
			User clonedUser = user.clone();
			clonedUser.clearRole();
			clonedAllUsers.add(clonedUser);
		}
		model.addAttribute("allUsers", clonedAllUsers); // TODO find all PILOTs only
		model.addAttribute("allLocations", this.locationRepository.findAll(sortByNameAsc()));
		model.addAttribute("allOperations", this.operationRepository.findAll(sortByNameAsc()));
		model.addAttribute("month", startDateCal.get(Calendar.MONTH));
		model.addAttribute("maxHobbsEnd", this.flightLogRepository.findMaxHobbsEndByAircraft(aircraft));
		return "flightLog";
	}

	@RequestMapping(value = "/flightLog/maxHobbsEnd/{aircraftId}", method = RequestMethod.GET)
	@ResponseBody
	public BigDecimal findMaxHobbsEndByAircraft(@PathVariable(value = "aircraftId") Long aircraftId) {
		Aircraft aircraft = new Aircraft();
		aircraft.setId(aircraftId);
		return this.flightLogRepository.findMaxHobbsEndByAircraft(aircraft);
	}
}
