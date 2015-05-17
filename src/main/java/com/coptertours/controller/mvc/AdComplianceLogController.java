package com.coptertours.controller.mvc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coptertours.domain.AdCompliance;
import com.coptertours.domain.AdComplianceLog;
import com.coptertours.domain.Aircraft;
import com.coptertours.options.Role;
import com.coptertours.repository.AdComplianceLogRepository;
import com.coptertours.repository.AdComplianceRepository;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.UserRepository;
import com.coptertours.util.DateUtil;

@Controller
public class AdComplianceLogController extends BaseController {
	@Autowired
	private AdComplianceRepository adComplianceRepository;
	@Autowired
	private AdComplianceLogRepository adComplianceLogRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AircraftRepository aircraftRepository;

	@RequestMapping("/adComplianceLog.html")
	String adComplianceLog(Model model, @RequestParam Long id, @RequestParam(required = false) Long adComplianceId, @RequestParam(required = false) Integer month) {
		Aircraft aircraft = this.aircraftRepository.findOne(id);

		List<AdCompliance> adCompliances = this.adComplianceRepository.findByModelAndDaily(aircraft.getModel(), true, sortByNameAsc());

		Calendar startDateCal = DateUtil.findMonthStartDate(month);
		Calendar endDateCal = DateUtil.findMonthEndDate(month);

		AdCompliance currentAdCompliance = null;
		List<AdComplianceLog> adComplianceLogs;
		if (!CollectionUtils.isEmpty(adCompliances)) {
			if (adComplianceId == null) {
				currentAdCompliance = adCompliances.get(0); // just get the first in the list and show the logs
			} else {
				currentAdCompliance = this.adComplianceRepository.findOne(adComplianceId);
			}
			adComplianceLogs = this.adComplianceLogRepository.findByAircraftIdAndAdComplianceIdAndComplyWithDateBetween(aircraft.getId(), currentAdCompliance.getId(), startDateCal.getTime(), endDateCal.getTime(), sortByComplyWithDate());
		} else {
			adComplianceLogs = new ArrayList<AdComplianceLog>();
		}
		model.addAttribute("aircraft", aircraft);
		model.addAttribute("currentAdCompliance", currentAdCompliance);
		model.addAttribute("adCompliances", adCompliances);
		model.addAttribute("adComplianceLogs", adComplianceLogs);
		model.addAttribute("allPilots", findUsersByRole(Role.PILOT));
		model.addAttribute("month", startDateCal.get(Calendar.MONTH));
		return "adComplianceLog";
	}
}
