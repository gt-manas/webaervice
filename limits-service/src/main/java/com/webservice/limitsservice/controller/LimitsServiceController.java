package com.webservice.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.limitsservice.configuration.Configuration;
import com.webservice.limitsservice.controller.bean.LimitConfiguration;

@RestController
public class LimitsServiceController {
	
	@Autowired
	private Configuration configuration;

	@GetMapping("/limit")
	public LimitConfiguration retriveLimitConfiguration() {
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
		
	}
}
