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

import com.coptertours.domain.Aircraft;
import com.coptertours.domain.MaintenanceLog;
import com.coptertours.domain.MaintenanceType;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.MaintenanceLogRepository;
import com.coptertours.repository.MaintenanceTypeRepository;

@Controller
public class DashboardController extends BaseController {
	@Autowired
	AircraftRepository aircraftRepository;
	@Autowired
	private MaintenanceTypeRepository maintenanceTypeRepository;
	@Autowired
	private MaintenanceLogRepository maintenanceLogRepository;

	@RequestMapping("/dashboard.html")
	String ratings(Model model) {
		List<Aircraft> aircrafts = this.aircraftRepository.findAll();
		for (Aircraft aircraft : aircrafts) {
			List<MaintenanceLog> maintenanceLogs = this.maintenanceLogRepository.findByAircraftId(aircraft.getId());
			Map<Long, MaintenanceLog> maintenanceTypeToLog = new HashMap<Long, MaintenanceLog>();
			for (MaintenanceLog maintenanceLog : maintenanceLogs) {
				maintenanceTypeToLog.put(maintenanceLog.getMaintenanceTypeId(), maintenanceLog);
			}

			List<MaintenanceType> maintenanceTypes = maintenanceTypeRepository.findByModelAndShowOnDashboardTrue(aircraft.getModel(), sortByMaintCategoryThenName());
			List<MaintenanceType> clonedMaintenanceTypes = new ArrayList<MaintenanceType>(maintenanceTypes.size());
			for (MaintenanceType maintType : maintenanceTypes) {
				MaintenanceLog log = maintenanceTypeToLog.get(maintType.getId());
				MaintenanceType clonedMaintenanceType = maintType.clone();
				if (log != null) {
					clonedMaintenanceType.setMaintenanceLog(log);
				}
				clonedMaintenanceTypes.add(clonedMaintenanceType);
			}
			aircraft.setMaintenanceTypes(clonedMaintenanceTypes);
		}
		model.addAttribute("aircrafts", aircrafts);
		model.addAttribute("today", new Date());
		return "dashboard";
	}
}
