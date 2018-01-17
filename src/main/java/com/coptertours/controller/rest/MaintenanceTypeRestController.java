package com.coptertours.controller.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.MaintenanceType;
import com.coptertours.repository.MaintenanceTypeRepository;
import com.coptertours.repository.ModelRepository;

@RestController
@RequestMapping(value = "/admin/maintenanceTypes")
public class MaintenanceTypeRestController {
	@Autowired
	private MaintenanceTypeRepository maintenanceTypeRepository;
	@Autowired
	private ModelRepository modelRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<MaintenanceType> maintenanceTypes() {
		return this.maintenanceTypeRepository.findAll();
	}

	@RequestMapping(value = "/model/{modelId}", method = RequestMethod.GET)
	@ResponseBody
	public Collection<MaintenanceType> maintenanceTypesByModel(@PathVariable Long modelId) {
		com.coptertours.domain.Model model = this.modelRepository.findOne(modelId);
		return this.maintenanceTypeRepository.findByModelAndActiveTrue(model, new Sort(Sort.Direction.ASC, "name"));
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