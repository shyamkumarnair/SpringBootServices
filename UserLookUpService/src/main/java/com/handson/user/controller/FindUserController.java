package com.handson.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handson.user.UserLookUpServiceApplication;
import com.handson.user.json.Response;
import com.handson.user.service.FindUserService;

@RestController
@RequestMapping("/users")
public class FindUserController {

	@Autowired
	private FindUserService service;

	private static final Logger log = LoggerFactory.getLogger(UserLookUpServiceApplication.class);

	@GetMapping("/city/{city}/distance/{distance}")
	public Response getUsers(@PathVariable String city, @PathVariable String distance) {
		log.debug("getUser request received, city [%s] , distance [%s] ", city, distance);
		return service.getUsers(city, distance);
	}

	@GetMapping("/city/{city}")
	public Response getUsers(@PathVariable String city) {
		log.debug("getUser request received, city [%s] ", city);
		return service.getUsers(city, "0.0");
	}

	@GetMapping("/london")
	public Response getLondonUsers() {
		log.debug("getLondonUsers request received");
		return service.getUsers("London", "50");
	}

}
