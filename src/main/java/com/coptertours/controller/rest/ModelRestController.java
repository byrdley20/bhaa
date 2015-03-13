package com.coptertours.controller.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.Model;
import com.coptertours.repository.ModelRepository;

@RestController
@RequestMapping(value = "/models")
public class ModelRestController {
	@Autowired
	ModelRepository modelRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Model> models() {
		return this.modelRepository.findAll();
	}
}
