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

import com.coptertours.domain.User;
import com.coptertours.repository.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {
	@Autowired
	UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<User> users() {
		return this.userRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	User addUser(@RequestBody User user, final HttpServletResponse response) {
		return this.userRepository.save(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteUser(@PathVariable Long id) {
		this.userRepository.delete(id);
	}
}
