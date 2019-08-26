package com.webservice.currencyexchangeservice.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.currencyexchangeservice.repository.ExchangeRepository;
import com.webservice.currencyexchangeservice.vo.ExchangeValue;

@RestController
class CurrencyExchangeController {

	@Autowired
    private Environment environment;
	
	@Autowired
	private ExchangeRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from ,@PathVariable String to) {
		ExchangeValue exchangeValue = repository.findByFromAndTo(from,to);
		//exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}

}
