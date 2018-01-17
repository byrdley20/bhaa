package com.coptertours.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelpController extends BaseController {
	@RequestMapping("/help.html")
	String help(Model model) {
		return "help";
	}
}
