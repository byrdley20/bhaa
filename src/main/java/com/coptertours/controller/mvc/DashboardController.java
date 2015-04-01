package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.repository.AircraftRepository;

@Controller
public class DashboardController extends BaseController {
	@Autowired
	AircraftRepository aircraftRepository;

	@RequestMapping("/dashboard.html")
	String ratings(Model model) {
		model.addAttribute("aircrafts", this.aircraftRepository.findAll());
		return "dashboard";
	}
}
