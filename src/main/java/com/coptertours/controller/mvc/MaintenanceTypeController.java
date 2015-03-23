package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.repository.MaintenanceTypeRepository;

@Controller
public class MaintenanceTypeController {
	@Autowired
	MaintenanceTypeRepository maintenanceTypeRepository;

	@RequestMapping("/maintenanceTypes.html")
	String maintenanceTypes(Model model) {
		model.addAttribute("maintenanceTypes",
				this.maintenanceTypeRepository.findAll());
		return "maintenanceTypes";
	}
}
