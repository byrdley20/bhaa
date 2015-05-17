package com.coptertours.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;

import com.coptertours.domain.AdCompliance;
import com.coptertours.domain.User;
import com.coptertours.repository.AdComplianceRepository;
import com.coptertours.repository.UserRepository;

public class BaseRestController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdComplianceRepository adComplianceRepository;

	protected void resetRole(User user) {
		if (user != null) {
			User foundUser = this.userRepository.findOne(user.getId());
			if (foundUser != null) {
				user.setRole(foundUser.getRole());
			}
		}
	}

	protected void resetModel(AdCompliance adCompliance) {
		if (adCompliance != null) {
			AdCompliance foundAdCompliance = this.adComplianceRepository.findOne(adCompliance.getId());
			if (adCompliance != null) {
				adCompliance.setModel(foundAdCompliance.getModel());
			}
		}
	}
}
