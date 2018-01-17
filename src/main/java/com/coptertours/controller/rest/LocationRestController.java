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

import com.coptertours.domain.Location;
import com.coptertours.repository.LocationRepository;

@RestController
@RequestMapping(value = "/admin/locations")
public class LocationRestController {
	@Autowired
	LocationRepository locationRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Location> locations() {
		return this.locationRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	Location addLocation(@RequestBody Location location,
			final HttpServletResponse response) {
		return this.locationRepository.save(location);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteModel(@PathVariable Long id) {
		this.locationRepository.delete(id);
	}
}
