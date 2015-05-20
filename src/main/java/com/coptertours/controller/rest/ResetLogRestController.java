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

import com.coptertours.domain.ResetLog;
import com.coptertours.repository.ResetLogRepository;

@RestController
@RequestMapping(value = "/resetLogs")
public class ResetLogRestController extends BaseRestController {
	@Autowired
	private ResetLogRepository resetLogRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<ResetLog> resetLogs() {
		return this.resetLogRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	ResetLog addResetLog(@RequestBody ResetLog resetLog, final HttpServletResponse response) {
		return this.resetLogRepository.save(resetLog);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteResetLog(@PathVariable Long id) {
		this.resetLogRepository.delete(id);
	}
}
