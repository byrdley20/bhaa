package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.repository.RatingRepository;
import com.coptertours.repository.UserRepository;

@Controller
public class UserController {
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
	
	private Sort sortByNameAsc() {
        return new Sort(Sort.Direction.ASC, "name");
    }
}
