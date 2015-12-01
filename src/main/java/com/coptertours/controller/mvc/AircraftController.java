package com.coptertours.controller.mvc;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.domain.Aircraft;
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

	@RequestMapping(value = { "/admin/aircrafts.html", "/admin/aircraft.html" })
	String aircafts(Model model, HttpServletRequest request) throws IOException {
		List<Aircraft> allAircrafts = this.aircraftRepository.findAll();
		List<com.coptertours.domain.Model> allModels = this.modelRepository.findAllByActiveTrue(sortByNameAsc());
		model.addAttribute("aircrafts", allAircrafts);
		model.addAttribute("allModels", allModels);
		return "admin/aircrafts";
	}
}