package com.coptertours.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;

import com.coptertours.domain.AdCompliance;
import com.coptertours.domain.Aircraft;
import com.coptertours.domain.MaintenanceType;
import com.coptertours.domain.User;
import com.coptertours.repository.AdComplianceRepository;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.MaintenanceTypeRepository;
import com.coptertours.repository.UserRepository;

public class BaseRestController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdComplianceRepository adComplianceRepository;
	@Autowired
	private AircraftRepository aircraftRepository;
	@Autowired
	private MaintenanceTypeRepository maintenanceTypeRepository;

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
			if (foundAdCompliance != null) {
				adCompliance.setModel(foundAdCompliance.getModel());
			}
		}
	}

	protected void resetResetLogs(Aircraft aircraft) {
		if (aircraft != null) {
			Aircraft foundAircraft = this.aircraftRepository.findOne(aircraft.getId());
			if (foundAircraft != null) {
				aircraft.setResetLogs(foundAircraft.getResetLogs());
			}
		}
	}

	protected void resetMaintenanceTypeEnums(MaintenanceType maintenanceType) {
		if (maintenanceType != null) {
			MaintenanceType foundMaintenanceType = this.maintenanceTypeRepository.findOne(maintenanceType.getId());
			if (foundMaintenanceType != null) {
				maintenanceType.setAction(foundMaintenanceType.getAction());
				maintenanceType.setMaintenanceCategory(foundMaintenanceType.getMaintenanceCategory());
			}
		}
	}
}
