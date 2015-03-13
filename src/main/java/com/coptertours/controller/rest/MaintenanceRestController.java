package com.coptertours.controller.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.Maintenance;
import com.coptertours.repository.MaintenanceRepository;

@RestController
@RequestMapping(value = "/maintenances")
public class MaintenanceRestController {
	@Autowired
	MaintenanceRepository maintenanceRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Maintenance> maintenances() {
		return this.maintenanceRepository.findAll();
	}
}
