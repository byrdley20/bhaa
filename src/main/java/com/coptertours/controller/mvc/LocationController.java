package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.repository.LocationRepository;

@Controller
public class LocationController extends BaseController {
	@Autowired
	LocationRepository locationRepository;

	@RequestMapping("/admin/locations.html")
	String locations(Model model) {
		model.addAttribute("locations", this.locationRepository.findAll(sortByNameAsc()));
		return "admin/locations";
	}
}
