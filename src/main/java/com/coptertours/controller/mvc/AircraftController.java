package com.coptertours.controller.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.domain.AdCompliance;
import com.coptertours.domain.Aircraft;
import com.coptertours.repository.AdComplianceRepository;
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
	@Autowired
	private AdComplianceRepository adComplianceRepository;

	@RequestMapping(value = { "/admin/aircrafts.html", "/admin/aircraft.html" })
	String aircafts(Model model, HttpServletRequest request) throws IOException {
		List<Aircraft> allAircrafts = this.aircraftRepository.findAll();
		List<com.coptertours.domain.Model> allModels = this.modelRepository.findAllByActiveTrue(sortByNameAsc());

		Map<Long, List<AdCompliance>> modelToAdCompliances = new HashMap<Long, List<AdCompliance>>();
		for (Aircraft aircraft : allAircrafts) {
			configureAdCompliances(modelToAdCompliances, aircraft.getModel());
		}

		model.addAttribute("aircrafts", allAircrafts);
		model.addAttribute("allModels", allModels);
		return "admin/aircrafts";
	}

	private void configureAdCompliances(Map<Long, List<AdCompliance>> modelToAdCompliances, com.coptertours.domain.Model aircraftModel) {
		List<AdCompliance> adCompliancesForModel = modelToAdCompliances.get(aircraftModel.getId());
		if (adCompliancesForModel == null) {
			adCompliancesForModel = this.adComplianceRepository.findByModelAndDailyAndActiveTrue(aircraftModel, true, sortByNameAsc());
			if (!CollectionUtils.isEmpty(adCompliancesForModel)) {
				aircraftModel.setHasAdCompliances(true);
			}
			modelToAdCompliances.put(aircraftModel.getId(), adCompliancesForModel);
		} else if (!CollectionUtils.isEmpty(adCompliancesForModel)) {
			aircraftModel.setHasAdCompliances(true);
		}
	}
}