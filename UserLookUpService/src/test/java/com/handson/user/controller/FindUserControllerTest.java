package com.handson.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.handson.user.json.Response;
import com.handson.user.service.FindUserService;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class FindUserControllerTest {

	@Autowired
	RestTemplate restTemplate;

	@MockBean
	private FindUserService mockService;

	@Test
	void testGet() throws Exception {
		ArgumentCaptor<String> cityArgument = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> distanceArgument = ArgumentCaptor.forClass(String.class);
		restTemplate.getForObject("http://localhost:8070/users/london/", Response.class);
		verify(mockService).getUsers(cityArgument.capture(), distanceArgument.capture());
		assertEquals("London", cityArgument.getValue());
		assertEquals("50", distanceArgument.getValue());
	}

	@Test
	void testCity() throws Exception {
		ArgumentCaptor<String> cityArgument = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> distanceArgument = ArgumentCaptor.forClass(String.class);
		restTemplate.getForObject("http://localhost:8070/users/city/{city}/", Response.class, "london");
		verify(mockService).getUsers(cityArgument.capture(), distanceArgument.capture());
		assertEquals("london", cityArgument.getValue());
		assertEquals("0.0", distanceArgument.getValue());
	}

	@Test
	void testCityandDistance() throws Exception {
		ArgumentCaptor<String> cityArgument = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> distanceArgument = ArgumentCaptor.forClass(String.class);
		restTemplate.getForObject("http://localhost:8070/users/city/{city}/distance/{distance}/", Response.class,
				"london", "50.00");
		verify(mockService).getUsers(cityArgument.capture(), distanceArgument.capture());
		assertEquals("london", cityArgument.getValue());
		assertEquals("50.00", distanceArgument.getValue());
	}
}
