package com.coptertours.controller.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.MaintenanceTypeRepository;
import com.coptertours.repository.ModelRepository;

@Controller
public class AircraftController extends BaseController {
	@Autowired
	AircraftRepository aircraftRepository;
	@Autowired
	ModelRepository modelRepository;
	@Autowired
	private MaintenanceTypeRepository maintenanceTypeRepository;

	@RequestMapping("/aircrafts.html")
	String aircafts(Model model) {
		model.addAttribute("aircrafts", this.aircraftRepository.findAll());
		List<com.coptertours.domain.Model> allModels = this.modelRepository.findAll(sortByNameAsc());
		model.addAttribute("allModels", allModels);
		return "aircrafts";
	}
}