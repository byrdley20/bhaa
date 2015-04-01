package com.coptertours.controller.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coptertours.domain.Aircraft;
import com.coptertours.domain.MaintenanceType;
import com.coptertours.options.MaintenanceCategory;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.ModelRepository;

@Controller
public class AircraftController extends BaseController {
	@Autowired
	AircraftRepository aircraftRepository;
	@Autowired
	ModelRepository modelRepository;

	@RequestMapping("/aircrafts.html")
	String aircafts(Model model) {
		model.addAttribute("aircrafts", this.aircraftRepository.findAll());
		List<com.coptertours.domain.Model> allModels = this.modelRepository.findAll(sortByNameAsc());
		for (com.coptertours.domain.Model aircraftModel : allModels) {
			// aircraftModel.prepareForSerialization();
		}
		model.addAttribute("allModels", allModels);
		return "aircrafts";
	}

	@RequestMapping("/aircraftStatus.html")
	String aircraftStatus(Model model, @RequestParam Long id) {
		Aircraft aircraft = this.aircraftRepository.findOne(id);
		
		List<MaintenanceType> flightHourMaintTypes = new ArrayList<MaintenanceType>();
		List<MaintenanceType> monthMaintTypes = new ArrayList<MaintenanceType>();
		for( MaintenanceType maintType : aircraft.getModel().getMaintenanceTypes() ) {
			if (MaintenanceCategory.FLIGHT_HOURS == maintType.getMaintenanceCategory()) {
				flightHourMaintTypes.add(maintType);
			} else if (MaintenanceCategory.MONTHS == maintType.getMaintenanceCategory()) {
				monthMaintTypes.add(maintType);
			}
		}
		
		model.addAttribute("aircraft", aircraft);
		model.addAttribute("flightHourMaintTypes", flightHourMaintTypes);
		model.addAttribute("monthMaintTypes", monthMaintTypes);
		return "aircraftStatus";
	}
}
