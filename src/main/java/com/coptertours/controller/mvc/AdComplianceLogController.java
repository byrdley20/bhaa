package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coptertours.repository.AdComplianceLogRepository;

@Controller
public class AdComplianceLogController extends BaseController {
	@Autowired
	AdComplianceLogRepository adComplianceLogRepository;

	@RequestMapping("/adComplianceLog.html")
	String adComplianceAircraft(Model model, @RequestParam Long aircraftId) {

	}
}
