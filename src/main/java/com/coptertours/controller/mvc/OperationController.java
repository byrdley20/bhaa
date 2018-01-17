package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.repository.OperationRepository;

@Controller
public class OperationController extends BaseController {
	@Autowired
	OperationRepository operationRepository;

	@RequestMapping("/admin/operations.html")
	String operations(Model model) {
		model.addAttribute("operations", this.operationRepository.findAll(sortByNameAsc()));
		return "admin/operations";
	}
}
