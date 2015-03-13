package com.coptertours.controller.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.Rating;
import com.coptertours.repository.RatingRepository;

@RestController
@RequestMapping(value = "/ratings")
public class RatingRestController {
	@Autowired
	RatingRepository ratingRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Rating> ratings() {
		return this.ratingRepository.findAll();
	}

	@RequestMapping(value = "/{ratingId}", method = RequestMethod.GET)
	@ResponseBody
	Rating findOne(@PathVariable Long ratingId) {
		return this.ratingRepository.findOne(ratingId);
	}
}
