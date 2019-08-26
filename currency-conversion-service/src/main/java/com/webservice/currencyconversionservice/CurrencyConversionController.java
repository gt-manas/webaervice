package com.webservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.webservice.currencyconversionservice.vo.CurrencyConvertionBean;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvertionBean retriveConversion(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		Map<String,String> uriVariable = new HashMap<>();
		
		uriVariable.put("from" , from );
		uriVariable.put("to", to);
		
		ResponseEntity<CurrencyConvertionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConvertionBean.class,
				uriVariable);
		
		CurrencyConvertionBean response = responseEntity.getBody();
		
		return new CurrencyConvertionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()));
		
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConvertionBean retriveConversionFeign(@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		CurrencyConvertionBean response = proxy.retriveExchangeValue(from, to); 
		
		return new CurrencyConvertionBean(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()));
		
	}

}
