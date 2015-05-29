package com.coptertours.controller.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.common.AppConstants;
import com.coptertours.common.ImageConverter;
import com.coptertours.domain.Aircraft;
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

	@SuppressWarnings("unchecked")
	@RequestMapping("/admin/aircrafts.html")
	String aircafts(Model model, HttpServletRequest request) throws IOException {
		List<Aircraft> allAircrafts = this.aircraftRepository.findAll();

		Map<String, String> imageMap = new HashMap<String, String>();
		Object imageMapObj = request.getSession().getAttribute(AppConstants.IMAGE_MAP);
		if (imageMapObj != null) {
			imageMap = (Map<String, String>) imageMapObj;
		}
		if (imageMap.size() != allAircrafts.size()) {
			for (Aircraft aircraft : allAircrafts) {
				if (!imageMap.containsKey(aircraft.getAircraftNumber()) && !StringUtils.isEmpty(aircraft.getImagePath())) {
					imageMap.put(aircraft.getAircraftNumber(), ImageConverter.convertToBase64(aircraft.getImagePath()));
				}
			}
		}
		request.getSession().setAttribute(AppConstants.IMAGE_MAP, imageMap);

		List<com.coptertours.domain.Model> allModels = this.modelRepository.findAll(sortByNameAsc());

		model.addAttribute("imageMap", imageMap);
		model.addAttribute("aircrafts", allAircrafts);
		model.addAttribute("allModels", allModels);
		return "admin/aircrafts";
	}
}