package com.coptertours.controller.mvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.domain.AdCompliance;
import com.coptertours.domain.AdComplianceLog;
import com.coptertours.domain.Aircraft;
import com.coptertours.domain.MaintenanceLog;
import com.coptertours.domain.MaintenanceType;
import com.coptertours.repository.AdComplianceLogRepository;
import com.coptertours.repository.AdComplianceRepository;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.FlightLogRepository;
import com.coptertours.repository.MaintenanceLogRepository;
import com.coptertours.repository.MaintenanceTypeRepository;
import com.coptertours.util.DateUtil;

@Controller
public class DashboardController extends BaseController {
	@Autowired
	AircraftRepository aircraftRepository;
	@Autowired
	private MaintenanceTypeRepository maintenanceTypeRepository;
	@Autowired
	private MaintenanceLogRepository maintenanceLogRepository;
	@Autowired
	private FlightLogRepository flightLogRepository;
	@Autowired
	private AdComplianceRepository adComplianceRepository;
	@Autowired
	private AdComplianceLogRepository adComplianceLogRepository;

	@RequestMapping("/dashboard.html")
	String dashboard(Model model) {
		Map<Long, List<AdCompliance>> modelToAdCompliances = new HashMap<Long, List<AdCompliance>>();
		List<Aircraft> aircrafts = this.aircraftRepository.findAll();
		Date todayStart = DateUtil.findDayStart(new Date());
		Date todayEnd = DateUtil.findDayEnd(new Date());

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

			Integer totalStarts = this.flightLogRepository.findTotalStartsByAircraft(aircraft);
			if (totalStarts != null) {
				aircraft.setTotalStarts(totalStarts);
			}
			com.coptertours.domain.Model aircraftModel = aircraft.getModel();

			List<AdCompliance> adCompliancesForModel = modelToAdCompliances.get(aircraftModel.getId());
			if (adCompliancesForModel == null) {
				adCompliancesForModel = this.adComplianceRepository.findByModelAndDaily(aircraftModel, true, sortByNameAsc());
				if (!CollectionUtils.isEmpty(adCompliancesForModel)) {
					aircraftModel.setHasAdCompliances(true);
				}
				modelToAdCompliances.put(aircraftModel.getId(), adCompliancesForModel);
			} else if (!CollectionUtils.isEmpty(adCompliancesForModel)) {
				aircraftModel.setHasAdCompliances(true);
			}

			List<AdComplianceLog> adComplianceLogsForToday = new ArrayList<AdComplianceLog>();
			for (AdCompliance adCompliance : adCompliancesForModel) {
				adComplianceLogsForToday.addAll(this.adComplianceLogRepository.findByAircraftIdAndAdComplianceIdAndComplyWithDateBetween(aircraft.getId(), adCompliance.getId(), todayStart, todayEnd, sortByComplyWithDate()));
			}
			// if all of the AD Compliances for this model have not been complied with today
			if (CollectionUtils.isEmpty(adComplianceLogsForToday) || adComplianceLogsForToday.size() != adCompliancesForModel.size()) {
				aircraft.setAdComplied(false);
			}
		}

		model.addAttribute("aircrafts", aircrafts);
		model.addAttribute("today", new Date());
		return "dashboard";
	}
}
