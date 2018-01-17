package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.repository.ModelRepository;

@Controller
public class ModelController extends BaseController {
	@Autowired
	ModelRepository modelRepository;

	@RequestMapping("/admin/models.html")
	String models(Model model) {
		model.addAttribute("models", this.modelRepository.findAll(sortByNameAsc()));
		return "admin/models";
	}
}
