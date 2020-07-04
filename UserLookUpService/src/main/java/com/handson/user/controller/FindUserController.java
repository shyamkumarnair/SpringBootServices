package com.handson.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handson.user.json.Location;
import com.handson.user.json.User;
import com.handson.user.service.FindUserService;

@RestController
@RequestMapping("/users")
public class FindUserController {

	@Autowired
	private FindUserService service;

	@GetMapping("/city/{city}/{distance}")
	public List<User> getUsers(@PathVariable String city, @PathVariable String distance){
		return service.getUsers(city, distance);
	}

	@GetMapping("/city/{city}")
	public Optional<Location> getUsers(@PathVariable String city) {
		return service.findLocation(city);
	}

}
