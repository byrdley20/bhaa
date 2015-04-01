package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.LocationRepository;
import com.coptertours.repository.OperationRepository;
import com.coptertours.repository.UserRepository;

@Controller
public class FlightLogController extends BaseController {

	@Autowired
	AircraftRepository aircraftRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	OperationRepository operationRepository;

	@RequestMapping("/flightLog.html")
	String flightLog(Model model, @RequestParam Long id) {
		model.addAttribute("aircraft", this.aircraftRepository.findOne(id));
		model.addAttribute("allUsers", this.userRepository.findAll(sortByLastNameAsc())); // TODO find all PILOTs only
		model.addAttribute("allLocations", this.locationRepository.findAll(sortByNameAsc()));
		model.addAttribute("allOperations", this.operationRepository.findAll(sortByNameAsc()));
		return "flightLog";
	}

}
