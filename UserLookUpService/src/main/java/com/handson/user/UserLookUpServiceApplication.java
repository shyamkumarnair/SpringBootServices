package com.handson.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import com.handson.user.service.FindUserService;

@SpringBootApplication
@EnableCircuitBreaker
public class UserLookUpServiceApplication {
	
	@Autowired
	private FindUserService userService;

	public static void main(String[] args) {
		SpringApplication.run(UserLookUpServiceApplication.class, args);
		System.out.println("Running application");
	}
	
	@Bean
	WebClient getWebClient()
	{
		return WebClient.create("https://bpdts-test-app.herokuapp.com");
	}

	
}
