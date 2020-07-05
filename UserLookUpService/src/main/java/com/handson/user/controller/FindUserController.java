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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(value = "User Lookup Service")
public class FindUserController {

	@Autowired
	private FindUserService service;

	private static final Logger log = LoggerFactory.getLogger(UserLookUpServiceApplication.class);

	@GetMapping("/city/{city}/distance/{distance}")
	@ApiOperation(value = "getUsers - return users based on their city and within a distance")
	public Response getUsers(@PathVariable String city, @PathVariable String distance) {
		log.info("getUser request received, city [%s] , distance [%s] ", city, distance);
		return service.getUsers(city, distance);
	}

	@GetMapping("/city/{city}")
	@ApiOperation(value = "getUsers- return users based on their city")
	public Response getUsers(@PathVariable String city) {
		log.info("getUser request received, city [%s] ", city);
		return service.getUsers(city, "0.0");
	}

	@GetMapping("/london")
	@ApiOperation(value = "getLondonUsers - returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London")
	public Response getLondonUsers() {
		log.info("getLondonUsers request received");
		return service.getUsers("London", "50");
	}

}
