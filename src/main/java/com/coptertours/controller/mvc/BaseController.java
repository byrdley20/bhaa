package com.coptertours.controller.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.coptertours.domain.BaseDomain;
import com.coptertours.domain.User;
import com.coptertours.options.Role;
import com.coptertours.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseController {

	@Autowired
	private UserRepository userRepository;

	public String toJson(List<? extends BaseDomain> domainList) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(domainList);
	}

	protected List<User> findUsersByRole(Role role) {
		List<User> users = this.userRepository.findByRole(role, sortByLastNameAsc());
		List<User> clonedUsers = new ArrayList<User>(users.size());
		for (User user : users) {
			User clonedUser = user.clone();
			clonedUser.clearRole();
			clonedUsers.add(clonedUser);
		}
		return clonedUsers;
	}

	protected Sort sortByNameAsc() {
		return new Sort(Sort.Direction.ASC, "name");
	}

	protected Sort sortByLastNameAsc() {
		return new Sort(Sort.Direction.ASC, "lastName");
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
