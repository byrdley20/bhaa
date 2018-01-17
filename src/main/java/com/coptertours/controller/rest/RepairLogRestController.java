package com.coptertours.controller.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.RepairLog;
import com.coptertours.repository.RepairLogRepository;
import com.coptertours.repository.UserRepository;

@RestController
@RequestMapping(value = "/squawks")
public class RepairLogRestController extends BaseRestController {
	@Autowired
	private RepairLogRepository repairLogRepository;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<RepairLog> repairLogs() {
		return this.repairLogRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	RepairLog addRepairLog(@RequestBody RepairLog repairLog, final HttpServletResponse response) {
		this.resetRoles(repairLog);
		return this.repairLogRepository.save(repairLog);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteRepairLog(@PathVariable Long id) {
		this.repairLogRepository.delete(id);
	}

	private void resetRoles(RepairLog repairLog) {
		resetRole(repairLog.getPilot());
		resetRole(repairLog.getMechanic());
	}
}
