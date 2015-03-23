package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.ModelRepository;

@Controller
public class AircraftController {
	@Autowired
	AircraftRepository aircraftRepository;
	@Autowired
	ModelRepository modelRepository;

	@RequestMapping("/aircrafts.html")
	String aircafts(Model model) {
		model.addAttribute("aircrafts", this.aircraftRepository.findAll());
		model.addAttribute("allModels", this.modelRepository.findAll(sortByNameAsc()));
		return "aircrafts";
	}
	
	private Sort sortByNameAsc() {
        return new Sort(Sort.Direction.ASC, "name");
    }
}
