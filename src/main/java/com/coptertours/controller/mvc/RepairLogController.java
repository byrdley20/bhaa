package com.coptertours.controller.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.domain.Aircraft;
import com.coptertours.options.Role;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.RepairLogRepository;
import com.coptertours.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class RepairLogController extends BaseController {
	@Autowired
	RepairLogRepository repairLogRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AircraftRepository aircraftRepository;

	@RequestMapping("/squawks.html")
	String squawks(Model model) throws JsonProcessingException {

		model.addAttribute("allPilots", findUsersByRole(Role.PILOT));
		model.addAttribute("allMechanics", findUsersByRole(Role.MECHANIC));
		ObjectMapper mapper = new ObjectMapper();
		List<Aircraft> allAircrafts = this.aircraftRepository.findAllByActiveTrue(sortByAircraftNumberAsc());
		model.addAttribute("allAircrafts", allAircrafts);
		model.addAttribute("allAircraftsJSON", mapper.writeValueAsString(allAircrafts));
		model.addAttribute("openSquawks", this.repairLogRepository.findByRepairDateIsNull());
		return "repairLog";
	}

	@RequestMapping("/squawks-completed.html")
	String squawksCompleted(Model model) throws JsonProcessingException {
		model.addAttribute("allPilots", findUsersByRole(Role.PILOT));
		model.addAttribute("allMechanics", findUsersByRole(Role.MECHANIC));
		ObjectMapper mapper = new ObjectMapper();
		List<Aircraft> allAircrafts = this.aircraftRepository.findAllByActiveTrue(sortByAircraftNumberAsc());
		model.addAttribute("allAircrafts", allAircrafts);
		model.addAttribute("allAircraftsJSON", mapper.writeValueAsString(allAircrafts));
		model.addAttribute("completedSquawks", this.repairLogRepository.findByRepairDateIsNotNull());
		return "repairLogCompleted";
	}
}
