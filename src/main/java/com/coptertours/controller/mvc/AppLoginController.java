package com.coptertours.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppLoginController extends BaseController {
	@RequestMapping("/appLogin.html")
	String login(Model model) {
		return "appLogin";
	}
}
