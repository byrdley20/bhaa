package com.coptertours.controller.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.domain.User;
import com.coptertours.options.Role;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.RepairLogRepository;
import com.coptertours.repository.UserRepository;

@Controller
public class RepairLogController extends BaseController {
	@Autowired
	RepairLogRepository repairLogRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AircraftRepository aircraftRepository;

	@RequestMapping("/squawks.html")
	String squawks(Model model) {

		model.addAttribute("allPilots", findUsersByRole(Role.PILOT));
		model.addAttribute("allMechanics", findUsersByRole(Role.MECHANIC));
		model.addAttribute("allAircrafts", this.aircraftRepository.findAll(sortByNameAsc()));
		model.addAttribute("openSquawks", this.repairLogRepository.findByRepairDateIsNull());
		return "repairLog";
	}

	@RequestMapping("/squawks-completed.html")
	String squawksCompleted(Model model) {
		model.addAttribute("allPilots", findUsersByRole(Role.PILOT));
		model.addAttribute("allMechanics", findUsersByRole(Role.MECHANIC));
		model.addAttribute("allAircrafts", this.aircraftRepository.findAll(sortByNameAsc()));
		model.addAttribute("completedSquawks", this.repairLogRepository.findByRepairDateIsNotNull());
		return "repairLogCompleted";
	}

	private List<User> findUsersByRole(Role role) {
		List<User> users = this.userRepository.findByRole(role, sortByLastNameAsc());
		List<User> clonedUsers = new ArrayList<User>(users.size());
		for (User user : users) {
			User clonedUser = user.clone();
			clonedUser.clearRole();
			clonedUsers.add(clonedUser);
		}
		return clonedUsers;
	}
}
