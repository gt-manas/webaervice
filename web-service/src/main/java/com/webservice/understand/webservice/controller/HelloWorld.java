package com.webservice.understand.webservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	
	@GetMapping(path="/hello-world")
	public String getHelloWorld()
	{
		return "Hello World";
	}
	
	@GetMapping(path="/hello-world/pathVariable/{name}")
	public String getHelloWorld(@PathVariable String name)
	{
		return String.format("Hello World %s" ,name);
	}
}
