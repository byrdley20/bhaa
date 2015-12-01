package com.coptertours.controller.mvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.coptertours.domain.Aircraft;
import com.coptertours.domain.BaseDomain;
import com.coptertours.domain.User;
import com.coptertours.options.ResetItem;
import com.coptertours.options.Role;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.FlightLogRepository;
import com.coptertours.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AircraftRepository aircraftRepository;
	@Autowired
	private FlightLogRepository flightLogRepository;

	public String toJson(List<? extends BaseDomain> domainList) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(domainList);
	}

	protected List<User> findUsersByRole(Role role) {
		List<User> users = this.userRepository.findByRoleAndActiveTrue(role, sortByLastNameAsc());
		List<User> clonedUsers = new ArrayList<User>(users.size());
		for (User user : users) {
			User clonedUser = user.clone();
			clonedUser.clearRole();
			clonedUsers.add(clonedUser);
		}
		return clonedUsers;
	}

	protected void setCurrentHobbsAndOffsets(Aircraft aircraft) {
		BigDecimal maxHobbs = this.flightLogRepository.findMostRecentHobbsEndByAircraft(aircraft);
		aircraft.setHobbs(maxHobbs);
		BigDecimal hobbsOffset = this.aircraftRepository.findTotalOffsetByAircraftAndItem(aircraft.getId(), ResetItem.HOBBS);
		if (hobbsOffset != null) {
			aircraft.setHobbsOffset(hobbsOffset);
		}
		BigDecimal engineOffset = this.aircraftRepository.findTotalOffsetByAircraftAndItem(aircraft.getId(), ResetItem.ENGINE);
		if (engineOffset != null) {
			aircraft.setEngineTotalTimeOffset(engineOffset);
		}
	}

	protected Sort sortByNameAsc() {
		return new Sort(Sort.Direction.ASC, "name");
	}

	protected Sort sortByAircraftNumberAsc() {
		return new Sort(Sort.Direction.ASC, "aircraftNumber");
	}

	protected Sort sortByLastNameAsc() {
		return new Sort(Sort.Direction.ASC, "lastName");
	}

	protected Sort sortByUserLastNameAsc() {
		return new Sort(Sort.Direction.ASC, "user.lastName");
	}

	protected Sort sortByModelAsc() {
		return new Sort(Sort.Direction.ASC, "model.name");
	}

	protected Sort sortByMaintCategoryThenName() {
		return new Sort(new Order(Sort.Direction.ASC, "maintenanceCategory"), new Order(Sort.Direction.ASC, "name"));
	}

	protected Sort sortByDate() {
		return new Sort(Sort.Direction.ASC, "date");
	}

	protected Sort sortByComplyWithDate() {
		return new Sort(Sort.Direction.ASC, "complyWithDate");
	}

	protected Sort sortByTimeBeforeAction() {
		return new Sort(Sort.Direction.ASC, "timeBeforeAction");
	}
}
