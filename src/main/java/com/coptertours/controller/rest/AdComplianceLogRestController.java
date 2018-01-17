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

import com.coptertours.domain.AdComplianceLog;
import com.coptertours.repository.AdComplianceLogRepository;
import com.coptertours.repository.ModelRepository;

@RestController
@RequestMapping(value = "/adComplianceLog")
public class AdComplianceLogRestController extends BaseRestController {
	@Autowired
	private AdComplianceLogRepository adComplianceLogRepository;
	@Autowired
	private ModelRepository modelRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<AdComplianceLog> adComplianceLogs() {
		return this.adComplianceLogRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	AdComplianceLog addAdComplianceLog(@RequestBody AdComplianceLog adComplianceLog, final HttpServletResponse response) {
		this.resetRole(adComplianceLog.getPilot());
		return this.adComplianceLogRepository.save(adComplianceLog);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteAdComplianceLog(@PathVariable Long id) {
		this.adComplianceLogRepository.delete(id);
	}
}
