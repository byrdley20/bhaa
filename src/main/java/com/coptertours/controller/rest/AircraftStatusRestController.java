package com.coptertours.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.MaintenanceLog;
import com.coptertours.repository.MaintenanceLogRepository;

@RestController
@RequestMapping(value = "/aircraftStatus")
public class AircraftStatusRestController {
	@Autowired
	MaintenanceLogRepository aircraftStatusRepository;

	@RequestMapping(value = "/complyWith", method = RequestMethod.POST)
	@ResponseBody
	MaintenanceLog complyWith(@RequestBody MaintenanceLog maintenanceLog) {
		return this.aircraftStatusRepository.save(maintenanceLog);
	}
}