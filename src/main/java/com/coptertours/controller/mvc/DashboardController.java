package com.coptertours.controller.mvc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.domain.AdCompliance;
import com.coptertours.domain.AdComplianceLog;
import com.coptertours.domain.Aircraft;
import com.coptertours.domain.FlightLog;
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
	private AircraftRepository aircraftRepository;
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

	@RequestMapping({ "/", "/dashboard.html" })
	String dashboard(Model model, HttpServletRequest request) throws IOException {
		Map<Long, List<AdCompliance>> aircraftToAdCompliances = new HashMap<Long, List<AdCompliance>>();
		List<Aircraft> aircrafts = this.aircraftRepository.findAllByActiveTrue(sortByAircraftNumberAsc());
		Date today = new Date();
		Date todayStart = DateUtil.findDayStart(today);
		Date todayEnd = DateUtil.findDayEnd(today);
		Date yearStart = DateUtil.findYearStartDate(today);
		Date yearEnd = DateUtil.findYearEndDate(today);

		for (Aircraft aircraft : aircrafts) {
			List<MaintenanceLog> maintenanceLogs = this.maintenanceLogRepository.findByAircraftId(aircraft.getId());
			Map<Long, MaintenanceLog> maintenanceTypeToLog = new HashMap<Long, MaintenanceLog>();
			for (MaintenanceLog maintenanceLog : maintenanceLogs) {
				maintenanceTypeToLog.put(maintenanceLog.getMaintenanceTypeId(), maintenanceLog);
			}

			List<MaintenanceType> maintenanceTypes = maintenanceTypeRepository.findByModelAndAircraftAndShowOnDashboardTrueAndActiveTrue(aircraft.getModel(), aircraft);
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

			setCurrentHobbsAndOffsets(aircraft);

			Integer totalStarts = this.flightLogRepository.findTotalStartsByAircraftAndDateBetween(aircraft, yearStart, yearEnd);
			if (totalStarts != null) {
				aircraft.setTotalStarts(totalStarts);
			}

			List<FlightLog> yearFlightLogs = this.flightLogRepository.findByAircraftAndDateBetween(aircraft, yearStart, yearEnd, sortByDate());
			BigDecimal yearlyHours = BigDecimal.ZERO;
			for (FlightLog flightLog : yearFlightLogs) {
				yearlyHours = yearlyHours.add(flightLog.getHobbsEnd().subtract(flightLog.getHobbsBegin()));
			}
			aircraft.setYearlyHours(yearlyHours);

			com.coptertours.domain.Model aircraftModel = aircraft.getModel();

			configureAdCompliances(aircraftToAdCompliances, todayStart, todayEnd, aircraft, aircraftModel);
		}
		model.addAttribute("aircrafts", aircrafts);
		model.addAttribute("today", new Date());
		return "dashboard";
	}

	private void configureAdCompliances(Map<Long, List<AdCompliance>> aircraftToAdCompliances, Date todayStart, Date todayEnd, Aircraft aircraft, com.coptertours.domain.Model aircraftModel) {
		List<AdCompliance> adCompliancesForAircraft = aircraftToAdCompliances.get(aircraft.getId());
		if (adCompliancesForAircraft == null) {
			adCompliancesForAircraft = this.adComplianceRepository.findByModelAndAircraftAndDailyAndActiveTrue(aircraftModel.getId(), aircraft.getId(), true);
			if (!CollectionUtils.isEmpty(adCompliancesForAircraft)) {
				aircraft.setHasAdCompliances(true);
			}
			aircraftToAdCompliances.put(aircraft.getId(), adCompliancesForAircraft);
		} else if (!CollectionUtils.isEmpty(adCompliancesForAircraft)) {
			aircraft.setHasAdCompliances(true);
		}

		List<AdComplianceLog> adComplianceLogsForToday = new ArrayList<AdComplianceLog>();
		for (AdCompliance adCompliance : adCompliancesForAircraft) {
			adComplianceLogsForToday.addAll(this.adComplianceLogRepository.findByAircraftIdAndAdComplianceIdAndComplyWithDateBetween(aircraft.getId(), adCompliance.getId(), todayStart, todayEnd, sortByComplyWithDate()));
		}
		// if all of the AD Compliances for this model have not been complied with today
		if (CollectionUtils.isEmpty(adComplianceLogsForToday) || adComplianceLogsForToday.size() != adCompliancesForAircraft.size()) {
			aircraft.setAdComplied(false);
		}
	}
}
