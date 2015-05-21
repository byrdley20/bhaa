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

import com.coptertours.domain.Operation;
import com.coptertours.repository.OperationRepository;

@RestController
@RequestMapping(value = "/admin/operations")
public class OperationRestController {
	@Autowired
	OperationRepository operationRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Operation> operations() {
		return this.operationRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	Operation addOperation(@RequestBody Operation operation, final HttpServletResponse response) {
		return this.operationRepository.save(operation);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteOperation(@PathVariable Long id) {
		this.operationRepository.delete(id);
	}
}
