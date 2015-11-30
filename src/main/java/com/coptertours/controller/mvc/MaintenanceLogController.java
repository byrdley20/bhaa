package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.repository.AircraftRepository;

@Controller
public class MaintenanceLogController extends BaseController {
	@Autowired
	AircraftRepository aircraftRepository;

	@RequestMapping("/maintenanceLog.html")
	String maintenanceLog(Model model) {
		model.addAttribute("aircrafts", this.aircraftRepository.findAllByActiveTrue());
		return "maintenanceLog";
	}

	@RequestMapping("/aircraftMaintenanceLog.html")
	String aircraftMaintenanceLog(Model model) {
		model.addAttribute("aircrafts", this.aircraftRepository.findAllByActiveTrue());
		return "aircraftMaintenanceLog";
	}
}
