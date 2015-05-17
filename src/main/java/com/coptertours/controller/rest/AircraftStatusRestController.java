package com.coptertours.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.AdComplianceLog;
import com.coptertours.domain.MaintenanceLog;
import com.coptertours.repository.AdComplianceLogRepository;
import com.coptertours.repository.MaintenanceLogRepository;

@RestController
@RequestMapping(value = "/aircraftStatus")
public class AircraftStatusRestController {
	@Autowired
	private MaintenanceLogRepository maintenanceLogRepository;
	@Autowired
	private AdComplianceLogRepository adComplianceLogRepository;

	@RequestMapping(value = "/complyWith", method = RequestMethod.POST)
	@ResponseBody
	MaintenanceLog complyWith(@RequestBody MaintenanceLog maintenanceLog) {
		return this.maintenanceLogRepository.save(maintenanceLog);
	}

	@RequestMapping(value = "/complyWithAd", method = RequestMethod.POST)
	@ResponseBody
	AdComplianceLog complyWithAd(@RequestBody AdComplianceLog adComplianceLog) {
		return this.adComplianceLogRepository.save(adComplianceLog);
	}
}