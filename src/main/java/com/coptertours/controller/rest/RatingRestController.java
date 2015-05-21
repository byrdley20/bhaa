package com.coptertours.controller.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.Rating;
import com.coptertours.repository.RatingRepository;

@RestController
@RequestMapping(value = "/admin/ratings")
public class RatingRestController {
	@Autowired
	RatingRepository ratingRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Rating> ratings() {
		return this.ratingRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	Rating addRating(@RequestBody Rating rating,
			final HttpServletResponse response) {
		return this.ratingRepository.save(rating);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteModel(@PathVariable Long id) {
		this.ratingRepository.delete(id);
	}
}
