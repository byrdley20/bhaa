package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.options.Role;
import com.coptertours.repository.RatingRepository;
import com.coptertours.repository.UserRepository;

@Controller
public class UserController extends BaseController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RatingRepository ratingRepository;

	@RequestMapping("/admin/users.html")
	String users(Model model) {
		model.addAttribute("users", this.userRepository.findAll(sortByLastNameAsc()));
		model.addAttribute("allRatings", this.ratingRepository.findAllByActiveTrue(sortByNameAsc()));
		model.addAttribute("roleList", Role.asKeyValueList());
		return "admin/users";
	}
}
