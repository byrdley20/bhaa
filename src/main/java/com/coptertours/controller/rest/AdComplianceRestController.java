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

import com.coptertours.domain.AdCompliance;
import com.coptertours.repository.AdComplianceRepository;

@RestController
@RequestMapping(value = "/adCompliances")
public class AdComplianceRestController {
	@Autowired
	AdComplianceRepository adComplianceRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<AdCompliance> adCompliances() {
		return this.adComplianceRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	AdCompliance addAdCompliance(@RequestBody AdCompliance adCompliance, final HttpServletResponse response) {
		return this.adComplianceRepository.save(adCompliance);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteAdCompliance(@PathVariable Long id) {
		this.adComplianceRepository.delete(id);
	}
}
