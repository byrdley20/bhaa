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

import com.coptertours.domain.Model;
import com.coptertours.repository.ModelRepository;

@RestController
@RequestMapping(value = "/admin/models")
public class ModelRestController {
	@Autowired
	ModelRepository modelRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Model> models() {
		return this.modelRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	Model addAircraft(@RequestBody Model model,
			final HttpServletResponse response) {
		return this.modelRepository.save(model);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteModel(@PathVariable Long id) {
		this.modelRepository.delete(id);
	}
}
