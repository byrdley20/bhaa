package com.coptertours.controller.mvc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.domain.FlightLog;
import com.coptertours.domain.User;
import com.coptertours.repository.FlightLogRepository;
import com.coptertours.repository.UserRepository;
import com.coptertours.util.DateUtil;

@Controller
public class PilotHoursController extends BaseController {
	@Autowired
	FlightLogRepository flightLogRepository;
	@Autowired
	UserRepository userRepository;

	@RequestMapping("/admin/pilotHours.html")
	String pilotHours(Model model) {

		Map<User, BigDecimal> pilotHours = new LinkedHashMap<User, BigDecimal>();
		Map<User, BigDecimal> noPilotHours = new LinkedHashMap<User, BigDecimal>();

		Date beginningOfYear = DateUtil.findYearStartDate(new Date());
		List<User> users = this.userRepository.findAllByActiveTrue(sortByLastNameAsc());
		for (User user : users) {
			List<FlightLog> flightLogs = this.flightLogRepository.findByUserAndDateAfter(user, beginningOfYear);
			BigDecimal flightTime = BigDecimal.ZERO;
			if (flightLogs.size() > 0) {
				for (FlightLog flightLog : flightLogs) {
					flightTime = flightTime.add(flightLog.getHobbsEnd().subtract(flightLog.getHobbsBegin()));
				}
				if (user.getUserId().equals("XXXXXXX")) {
					noPilotHours.put(user, flightTime);
				} else {
					pilotHours.put(user, flightTime);
				}
			}
		}
		pilotHours.putAll(noPilotHours);

		// List<FlightLog> flightLogs = this.flightLogRepository.findByDateAfter(beginningOfYear, sortByUserLastNameAsc());
		//
		// for (FlightLog flightLog : flightLogs) {
		// BigDecimal flightTime = flightLog.getHobbsEnd().subtract(flightLog.getHobbsBegin());
		// User pilot = flightLog.getUser();
		// BigDecimal yearlyHours = pilotHours.get(pilot);
		// if(yearlyHours == null){
		// yearlyHours = BigDecimal.ZERO;
		// }
		// pilotHours.put(pilot, yearlyHours.add(flightTime));
		// }

		model.addAttribute("pilotHours", pilotHours);

		return "admin/pilotHours";
	}
}
