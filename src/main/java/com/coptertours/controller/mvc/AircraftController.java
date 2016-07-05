package com.coptertours.controller.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
import com.coptertours.domain.ExcludedAdCompliance;
import com.coptertours.domain.ExcludedMaintenanceType;
import com.coptertours.domain.MaintenanceType;
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
	public String aircafts(Model model, HttpServletRequest request) throws IOException {
		List<Aircraft> allAircrafts = this.aircraftRepository.findAll();
		List<com.coptertours.domain.Model> allModels = this.modelRepository.findAllByActiveTrue(sortByNameAsc());

		Map<Long, List<AdCompliance>> aircraftToAdCompliances = new HashMap<Long, List<AdCompliance>>();
		Map<Long, List<MaintenanceType>> aircraftToMaintenanceTypes = new HashMap<Long, List<MaintenanceType>>();
		for (Aircraft aircraft : allAircrafts) {
			configureAdCompliances(aircraftToAdCompliances, aircraft);
			configureMaintenanceTypes(aircraftToMaintenanceTypes, aircraft);
		}

		model.addAttribute("aircrafts", allAircrafts);
		model.addAttribute("allModels", allModels);
		return "admin/aircrafts";
	}

	private void configureAdCompliances(Map<Long, List<AdCompliance>> aircraftToAdCompliances, Aircraft aircraft) {
		com.coptertours.domain.Model aircraftModel = aircraft.getModel();
		List<AdCompliance> adCompliancesForAircraft = aircraftToAdCompliances.get(aircraft.getId());
		if (adCompliancesForAircraft == null) {
			adCompliancesForAircraft = this.adComplianceRepository.findByModelAndAircraftAndDailyAndActiveTrue(aircraftModel.getId(), aircraft.getId(), true);
			if (!CollectionUtils.isEmpty(adCompliancesForAircraft)) {
				aircraft.setHasAdCompliances(true);
			}
			aircraftToAdCompliances.put(aircraft.getId(), adCompliancesForAircraft);
		} else if (!CollectionUtils.isEmpty(adCompliancesForAircraft)) {
			aircraft.setHasAdCompliances(true);
		}
		List<AdCompliance> allAdCompliancesForModel = this.adComplianceRepository.findByModelAndActiveTrue(aircraftModel, sortByNameAsc());
		for (AdCompliance allAdComplianceForModel : allAdCompliancesForModel) {
			allAdComplianceForModel.clearModel();
		}
		for (ExcludedAdCompliance excludedAdCompliance : aircraft.getExcludedAdCompliances()) {
			excludedAdCompliance.getAdCompliance().clearModel();
			for (Iterator<AdCompliance> allAdCompliancesForModelIter = allAdCompliancesForModel.iterator(); allAdCompliancesForModelIter.hasNext();) {
				AdCompliance adCompliance = allAdCompliancesForModelIter.next();
				if (adCompliance.getId().equals(excludedAdCompliance.getAdCompliance().getId())) {
					allAdCompliancesForModelIter.remove();
				}
			}
		}
		aircraft.setAdCompliances(allAdCompliancesForModel);
	}

	private void configureMaintenanceTypes(Map<Long, List<MaintenanceType>> aircraftToMaintenanceTypes, Aircraft aircraft) {
		com.coptertours.domain.Model aircraftModel = aircraft.getModel();
		List<MaintenanceType> maintenanceTypesForAircraft = aircraftToMaintenanceTypes.get(aircraft.getId());
		if (maintenanceTypesForAircraft == null) {
			maintenanceTypesForAircraft = this.maintenanceTypeRepository.findByModelAndAircraftAndActiveTrue(aircraftModel, aircraft);
			aircraftToMaintenanceTypes.put(aircraft.getId(), maintenanceTypesForAircraft);
		}
		List<MaintenanceType> allMaintenanceTypesForModel = this.maintenanceTypeRepository.findByModelAndActiveTrue(aircraftModel, sortByNameAsc());
		for (MaintenanceType allMaintenanceTypeForModel : allMaintenanceTypesForModel) {
			allMaintenanceTypeForModel.clearModel();
			allMaintenanceTypeForModel.clearAction();
			allMaintenanceTypeForModel.clearMaintenanceCategory();
		}
		for (ExcludedMaintenanceType excludedMaintenanceType : aircraft.getExcludedMaintenanceTypes()) {
			excludedMaintenanceType.getMaintenanceType().clearModel();
			excludedMaintenanceType.getMaintenanceType().clearAction();
			excludedMaintenanceType.getMaintenanceType().clearMaintenanceCategory();
			for (Iterator<MaintenanceType> allMaintenanceTypesForModelIter = allMaintenanceTypesForModel.iterator(); allMaintenanceTypesForModelIter.hasNext();) {
				MaintenanceType maintenanceType = allMaintenanceTypesForModelIter.next();
				if (maintenanceType.getId().equals(excludedMaintenanceType.getMaintenanceType().getId())) {
					allMaintenanceTypesForModelIter.remove();
				}
			}
		}
		aircraft.setMaintenanceTypes(allMaintenanceTypesForModel);
	}
}