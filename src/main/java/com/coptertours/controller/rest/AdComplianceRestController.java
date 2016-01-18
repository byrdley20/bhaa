package com.coptertours.controller.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coptertours.domain.AdCompliance;
import com.coptertours.repository.AdComplianceRepository;
import com.coptertours.repository.ModelRepository;

@RestController
@RequestMapping(value = "/admin/adCompliances")
public class AdComplianceRestController {
	@Autowired
	private AdComplianceRepository adComplianceRepository;
	@Autowired
	private ModelRepository modelRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<AdCompliance> adCompliances() {
		return this.adComplianceRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public AdCompliance addAdCompliance(@RequestBody AdCompliance adCompliance, final HttpServletResponse response) {
		return this.adComplianceRepository.save(adCompliance);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteAdCompliance(@PathVariable Long id) {
		this.adComplianceRepository.delete(id);
	}

	@RequestMapping(value = "/model/{modelId}", method = RequestMethod.GET)
	@ResponseBody
	public Collection<AdCompliance> adCompliancesByModel(@PathVariable Long modelId) {
		com.coptertours.domain.Model model = this.modelRepository.findOne(modelId);
		return this.adComplianceRepository.findByModelAndActiveTrue(model, new Sort(Sort.Direction.ASC, "name"));
	}
}
