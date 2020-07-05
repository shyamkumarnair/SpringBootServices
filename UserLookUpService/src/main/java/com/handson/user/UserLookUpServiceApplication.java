package com.handson.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableCircuitBreaker
public class UserLookUpServiceApplication {

	@Autowired
	private RestTemplateBuilder builder;

	private static final Logger log = LoggerFactory.getLogger(UserLookUpServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserLookUpServiceApplication.class, args);
		log.info("UserLookUpServiceApplication is running");
	}

	@Bean
	RestTemplate getUserRestService() {
		return builder.build();
	}

}
