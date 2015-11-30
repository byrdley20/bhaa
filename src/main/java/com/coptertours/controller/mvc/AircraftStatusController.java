package com.coptertours.controller.mvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coptertours.domain.AdCompliance;
import com.coptertours.domain.AdComplianceLog;
import com.coptertours.domain.Aircraft;
import com.coptertours.domain.MaintenanceLog;
import com.coptertours.domain.MaintenanceType;
import com.coptertours.options.MaintenanceCategory;
import com.coptertours.options.Role;
import com.coptertours.repository.AdComplianceLogRepository;
import com.coptertours.repository.AdComplianceRepository;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.MaintenanceLogRepository;
import com.coptertours.repository.MaintenanceTypeRepository;

@Controller
public class AircraftStatusController extends BaseController {
	@Autowired
	AircraftRepository aircraftRepository;
	@Autowired
	private MaintenanceTypeRepository maintenanceTypeRepository;
	@Autowired
	private MaintenanceLogRepository maintenanceLogRepository;
	@Autowired
	private AdComplianceRepository adComplianceRepository;
	@Autowired
	private AdComplianceLogRepository adComplianceLogRepository;

	@RequestMapping("/aircraftStatus.html")
	String aircraftStatus(Model model, @RequestParam Long id) {
		Aircraft aircraft = this.aircraftRepository.findOne(id);

		List<MaintenanceLog> maintenanceLogs = this.maintenanceLogRepository.findByAircraftId(aircraft.getId());
		Map<Long, MaintenanceLog> maintenanceTypeToLog = new HashMap<Long, MaintenanceLog>();
		for (MaintenanceLog maintenanceLog : maintenanceLogs) {
			maintenanceTypeToLog.put(maintenanceLog.getMaintenanceTypeId(), maintenanceLog);
		}

		List<MaintenanceType> maintenanceTypes = this.maintenanceTypeRepository.findByModelAndActiveTrue(aircraft.getModel(), sortByMaintCategoryThenName());

		List<MaintenanceType> flightHourMaintTypes = new ArrayList<MaintenanceType>();
		List<MaintenanceType> monthMaintTypes = new ArrayList<MaintenanceType>();
		for (MaintenanceType maintType : maintenanceTypes) {
			maintType.setMaintenanceLog(maintenanceTypeToLog.get(maintType.getId()));
			if (MaintenanceCategory.FLIGHT_HOURS == maintType.getMaintenanceCategory()) {
				flightHourMaintTypes.add(maintType);
			} else if (MaintenanceCategory.MONTHS == maintType.getMaintenanceCategory()) {
				monthMaintTypes.add(maintType);
			}
		}

		boolean hasDailyAd = false;
		List<AdCompliance> adCompliances = new ArrayList<AdCompliance>();
		List<AdComplianceLog> adComplianceLogs = this.adComplianceLogRepository.findByAircraftId(id);
		Map<Long, AdComplianceLog> adComplianceToLog = new HashMap<Long, AdComplianceLog>();
		for (AdComplianceLog adComplianceLog : adComplianceLogs) {
			Long key = adComplianceLog.getAdComplianceId();
			AdComplianceLog existingAdComplianceLog = adComplianceToLog.get(key);
			if (existingAdComplianceLog == null ||
					existingAdComplianceLog.getComplyWithDate() == null ||
					existingAdComplianceLog.getComplyWithDate().before(adComplianceLog.getComplyWithDate())) {
				adComplianceToLog.put(key, adComplianceLog);
			}
		}
		List<AdCompliance> adCompliancesForModel = this.adComplianceRepository.findByModelAndActiveTrue(aircraft.getModel(), sortByTimeBeforeAction());
		for (AdCompliance adComplianceForModel : adCompliancesForModel) {
			adComplianceForModel.setAdComplianceLog(adComplianceToLog.get(adComplianceForModel.getId()));
			adCompliances.add(adComplianceForModel);
			if (adComplianceForModel.getDaily()) {
				hasDailyAd = true;
			}
		}

		setCurrentHobbsAndOffsets(aircraft);

		model.addAttribute("aircraft", aircraft);
		model.addAttribute("flightHourMaintTypes", flightHourMaintTypes);
		model.addAttribute("monthMaintTypes", monthMaintTypes);
		model.addAttribute("adCompliances", adCompliances);
		model.addAttribute("allPilots", findUsersByRole(Role.PILOT));
		model.addAttribute("today", new Date());
		model.addAttribute("hasDailyAd", hasDailyAd);
		return "aircraftStatus";
	}
}
