package com.coptertours.controller.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.domain.Rating;
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
		model.addAttribute("allRatings", this.ratingRepository.findAll());
		List<Rating> allRatings = this.ratingRepository.findAll();
		Map<Long, Rating> ratingsMap = new HashMap<Long, Rating>();
		for (Rating rating : allRatings) {
			// TODO add to map, then access in template
		}
		return "users";
	}
}
