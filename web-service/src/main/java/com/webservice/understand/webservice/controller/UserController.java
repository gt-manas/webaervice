package com.webservice.understand.webservice.controller;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webservice.understand.webservice.bean.User;
import com.webservice.understand.webservice.exception.UserNotFoundException;
import com.webservice.understand.webservice.service.UserDaoService;

@RestController
public class UserController {

	@Autowired
	UserDaoService userDaoService;

	@GetMapping(path = "/users")
	public List<User> retriveAllUser() {
		return userDaoService.findAll();
	}

	//HEATOAS
	@GetMapping(path = "/users/{id}")
	public Resource<User> retriveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if(user == null) 
			throw new UserNotFoundException("id - " +id);
			
		Resource<User> resource = new Resource<User>(user);

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveAllUser());
		
		resource.add(linkTo.withRel("all-user"));
		
		return resource;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		User savedUser = userDaoService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	
	  @DeleteMapping(path = "/users/{id}") 
	  public void removeUser(@PathVariable int id) { 
		  User user = userDaoService.deleteById(id);
		  if(user == null) {
			  throw new UserNotFoundException("Id - " + id );
		  }
	  }
	 

}
