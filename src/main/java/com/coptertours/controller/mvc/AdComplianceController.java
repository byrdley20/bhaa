package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coptertours.repository.AdComplianceRepository;
import com.coptertours.repository.ModelRepository;

@Controller
public class AdComplianceController extends BaseController {
	@Autowired
	AdComplianceRepository adComplianceRepository;
	@Autowired
	ModelRepository modelRepository;

	@RequestMapping("/admin/adComplianceChooseModel.html")
	public String adCompliance(Model model) {
		model.addAttribute("models", this.modelRepository.findAllByActiveTrue(sortByNameAsc()));
		return "admin/adComplianceChooseModel";
	}

	@RequestMapping("/admin/adCompliance.html")
	public String adCompliance(Model model, @RequestParam Long id) {
		com.coptertours.domain.Model aircraftModel = this.modelRepository.findById(id);

		model.addAttribute("model", aircraftModel);
		model.addAttribute("adCompliances", this.adComplianceRepository.findByModel(aircraftModel, sortByNameAsc()));
		return "admin/adCompliance";
	}
}
