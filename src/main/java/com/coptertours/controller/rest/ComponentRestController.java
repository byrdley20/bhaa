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

import com.coptertours.domain.Component;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.ComponentRepository;

@RestController
@RequestMapping(value = "/admin/components")
public class ComponentRestController extends BaseRestController {
	@Autowired
	private ComponentRepository componentRepository;
	@Autowired
	private AircraftRepository aircraftRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Component> components() {
		return this.componentRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	Component addComponent(@RequestBody Component component, final HttpServletResponse response) {
		this.resetResetLogs(component.getAircraft());
		this.resetMaintenanceTypeEnums(component.getMaintenanceType());
		return this.componentRepository.save(component);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteComponent(@PathVariable Long id) {
		this.componentRepository.delete(id);
	}
}
