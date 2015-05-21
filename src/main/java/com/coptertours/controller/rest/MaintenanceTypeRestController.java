package com.coptertours.controller.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.MaintenanceType;
import com.coptertours.repository.MaintenanceTypeRepository;

@RestController
@RequestMapping(value = "/admin/maintenanceTypes")
public class MaintenanceTypeRestController {
	@Autowired
	MaintenanceTypeRepository maintenanceTypeRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<MaintenanceType> maintenanceTypes() {
		return this.maintenanceTypeRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	MaintenanceType addMaintenanceType(
			@RequestBody MaintenanceType maintenanceType,
			final HttpServletResponse response) {
		return this.maintenanceTypeRepository.save(maintenanceType);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteModel(@PathVariable Long id) {
		this.maintenanceTypeRepository.delete(id);
	}
}