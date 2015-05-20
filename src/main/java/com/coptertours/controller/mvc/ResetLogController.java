package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coptertours.domain.Aircraft;
import com.coptertours.options.ResetItem;
import com.coptertours.repository.AircraftRepository;

@Controller
public class ResetLogController extends BaseController {
	@Autowired
	private AircraftRepository aircraftRepository;

	@RequestMapping("/resetLog.html")
	String resetLog(Model model, @RequestParam Long id, @RequestParam String i) {
		Aircraft aircraft = this.aircraftRepository.findOne(id);
		model.addAttribute("aircraft", aircraft);
		model.addAttribute("resetItem", ResetItem.byName(i));
		return "resetLog";
	}
}
