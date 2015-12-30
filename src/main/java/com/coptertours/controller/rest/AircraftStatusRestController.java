package com.coptertours.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.AdComplianceLog;
import com.coptertours.domain.Aircraft;
import com.coptertours.domain.Component;
import com.coptertours.domain.MaintenanceLog;
import com.coptertours.domain.MaintenanceType;
import com.coptertours.repository.AdComplianceLogRepository;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.ComponentRepository;
import com.coptertours.repository.MaintenanceLogRepository;
import com.coptertours.repository.MaintenanceTypeRepository;

@RestController
@RequestMapping(value = "/aircraftStatus")
public class AircraftStatusRestController extends BaseRestController {
	@Autowired
	private MaintenanceLogRepository maintenanceLogRepository;
	@Autowired
	private AdComplianceLogRepository adComplianceLogRepository;
	@Autowired
	private ComponentRepository componentRepository;
	@Autowired
	private MaintenanceTypeRepository maintenanceTypeRepository;
	@Autowired
	private AircraftRepository aircraftRepository;

	@RequestMapping(value = "/complyWith", method = RequestMethod.POST)
	@ResponseBody
	MaintenanceLog complyWith(@RequestBody MaintenanceLog maintenanceLog) {
		MaintenanceLog currentMaintenanceLog = this.maintenanceLogRepository.findOne(maintenanceLog.getId());
		if (maintenanceLog.getComplyWithDate() == null) {
			maintenanceLog.setComplyWithDate(currentMaintenanceLog.getComplyWithDate());
		}
		if (maintenanceLog.getComplyWithHobbs() == null) {
			maintenanceLog.setComplyWithHobbs(currentMaintenanceLog.getComplyWithHobbs());
		}

		MaintenanceLog savedMaintenanceLog = this.maintenanceLogRepository.save(maintenanceLog);

		MaintenanceType maintenanceType = maintenanceTypeRepository.findOne(maintenanceLog.getMaintenanceTypeId());
		Aircraft aircraft = aircraftRepository.findOne(maintenanceLog.getAircraftId());
		Component linkedComponent = componentRepository.findByAircraftAndMaintenanceType(aircraft, maintenanceType);
		if (linkedComponent != null) {
			linkedComponent.setPartNumber(maintenanceLog.getPartNumber());
			linkedComponent.setSerialNumber(maintenanceLog.getSerialNumber());
			savedMaintenanceLog.setLinkedComponentId(linkedComponent.getId());
			componentRepository.save(linkedComponent);
		}
		return savedMaintenanceLog;
	}

	@RequestMapping(value = "/complyWithAd", method = RequestMethod.POST)
	@ResponseBody
	AdComplianceLog complyWithAd(@RequestBody AdComplianceLog adComplianceLog) {
		this.resetRole(adComplianceLog.getPilot());
		return this.adComplianceLogRepository.save(adComplianceLog);
	}

	@RequestMapping(value = "/componentUpdate", method = RequestMethod.POST)
	@ResponseBody
	Component componentUpdate(@RequestBody Component component) {
		if (component.getLinkedMaintenanceTypeId() > 0) {
			MaintenanceType linkedMaintenanceType = this.maintenanceTypeRepository.findOne(component.getLinkedMaintenanceTypeId());
			if (linkedMaintenanceType != null) {
				component.setMaintenanceType(linkedMaintenanceType);
				MaintenanceLog linkedMaintenanceLog = this.maintenanceLogRepository.findByAircraftIdAndMaintenanceTypeId(component.getAircraft().getId(), linkedMaintenanceType.getId());
				if (linkedMaintenanceLog == null) {
					linkedMaintenanceLog = new MaintenanceLog();
					linkedMaintenanceLog.setAircraftId(component.getAircraft().getId());
					linkedMaintenanceLog.setMaintenanceTypeId(linkedMaintenanceType.getId());
				}
				linkedMaintenanceLog.setPartNumber(component.getPartNumber());
				linkedMaintenanceLog.setSerialNumber(component.getSerialNumber());
				this.maintenanceLogRepository.save(linkedMaintenanceLog);
			}
		}
		this.resetResetLogs(component.getAircraft());
		return this.componentRepository.save(component);
	}
}