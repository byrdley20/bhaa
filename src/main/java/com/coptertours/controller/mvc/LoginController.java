package com.coptertours.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController extends BaseController {
	@RequestMapping("/login.html")
	String operations(Model model) {
		return "login";
	}
}
