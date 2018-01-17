package com.coptertours.controller.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coptertours.domain.Aircraft;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.ComponentRepository;
import com.coptertours.repository.MaintenanceTypeRepository;

@Controller
public class ComponentController extends BaseController {
	@Autowired
	private ComponentRepository componentRepository;
	@Autowired
	private AircraftRepository aircraftRepository;
	@Autowired
	private MaintenanceTypeRepository maintenanceTypeRepository;

	@RequestMapping("/admin/components.html")
	String components(Model model) {
		List<Aircraft> aircraft = this.aircraftRepository.findAllByActiveTrue(sortByNameAsc());
		model.addAttribute("aircraft", aircraft);
		return "admin/components";
	}

	@RequestMapping("/admin/aircraftComponents.html")
	String aircraftComponents(Model model, @RequestParam Long id) {
		Aircraft aircraft = this.findAircraftById(id);
		model.addAttribute("components", this.findComponentsByAircraft(aircraft, sortByComponentNameAsc()));
		model.addAttribute("aircraft", aircraft);
		model.addAttribute("maintenanceTypes", this.findMaintenanceTypesByModelAndActiveTrue(aircraft.getModel(), sortByNameAsc()));
		return "admin/aircraftComponents";
	}
}
