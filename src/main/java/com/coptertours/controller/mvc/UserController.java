package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.repository.RatingRepository;
import com.coptertours.repository.UserRepository;

@Controller
public class UserController extends BaseController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RatingRepository ratingRepository;

	@RequestMapping("/users.html")
	String users(Model model) {
		model.addAttribute("users", this.userRepository.findAll());
		model.addAttribute("allRatings", this.ratingRepository.findAll(sortByNameAsc()));
		return "users";
	}
}
