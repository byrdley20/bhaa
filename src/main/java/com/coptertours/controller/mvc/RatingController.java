package com.coptertours.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coptertours.repository.RatingRepository;

@Controller
public class RatingController extends BaseController {
	@Autowired
	RatingRepository ratingRepository;

	@RequestMapping("/admin/ratings.html")
	String ratings(Model model) {
		model.addAttribute("ratings", this.ratingRepository.findAll(sortByNameAsc()));
		return "admin/ratings";
	}
}
