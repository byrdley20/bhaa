package com.coptertours.controller.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.Location;
import com.coptertours.repository.LocationRepository;

@RestController
@RequestMapping(value = "/locations")
public class LocationRestController {
	@Autowired
	LocationRepository locationRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Location> locations() {
		return this.locationRepository.findAll();
	}
}
