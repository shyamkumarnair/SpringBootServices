package com.handson.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class UserLookUpServiceApplicationTests {

	@Autowired
	MockMvc mockMvc;

	private Fixture fixture = new Fixture();;

	@Test
	void testGetUsers_validInput() {
		try {
			fixture.callGetUsers("London", "50");
			fixture.confirmValidResponseReceived();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testGetUsers_inValidCity() {
		try {
			fixture.callGetUsers("Lon", "50");
			fixture.confirmValidResponseReceived();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testGetUsers_noCity() {
		try {
			fixture.callGetUsers("", "50");
			fixture.confirmInvalidResponseReceived();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testGetUsers_noCitynoDistance() {
		try {
			fixture.callGetUsers("", "");
			fixture.confirmInvalidResponseReceived();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testGetUsers_noDistance() {
		try {
			fixture.callGetUsers("London");
			fixture.confirmValidResponseReceived();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testGetUsers_inValidDistance_alpha() {
		try {
			fixture.callGetUsers("London", "abc");
			fixture.confirmInvalidResponseReceived();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testGetUsers_inValidDistance_alphaNumeric() {
		try {
			fixture.callGetUsers("London", "abc50");
			fixture.confirmInvalidResponseReceived();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testGetUsers_inValidDistance_bigNumber() {
		try {
			fixture.callGetUsers("London", "43432423423.23432");
			fixture.confirmValidResponseReceived();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testGetUsers_withValidCityAlone() {
		try {
			fixture.callGetUsers("London");
			fixture.confirmValidResponseReceived();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testGetLondonUsers() {
		try {
			fixture.callGetUsers();
			fixture.confirmValidResponseReceived();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	private class Fixture {

		private MvcResult mvcResult;

		private String serviceUrl = "/users/city/%s/distance/%s/";
		private String serviceUrlwithCityAlone = "/users/city/%s/";
		private String serviceUrlForLondoners = "/users/london/";

		public void confirmInvalidResponseReceived() throws UnsupportedEncodingException {
			assertFalse(mvcResult.getResponse().getContentAsString().contains(HttpStatus.OK.name()));
			assertFalse(mvcResult.getResponse().getContentAsString().contains("success"));
		}

		public void callGetUsers() throws Exception {
			mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(String.format(serviceUrlForLondoners))
					.accept(MediaType.APPLICATION_JSON)).andReturn();
		}

		public void callGetUsers(String city) throws Exception {
			mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(String.format(serviceUrlwithCityAlone, city))
					.accept(MediaType.APPLICATION_JSON)).andReturn();
		}

		public void confirmValidResponseReceived() throws UnsupportedEncodingException {
			assertTrue(mvcResult.getResponse().getContentAsString().contains(HttpStatus.OK.name()));
			assertTrue(mvcResult.getResponse().getContentAsString().contains("success"));
		}

		public void callGetUsers(String city, String distance) throws Exception {
			mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(String.format(serviceUrl, city, distance))
					.accept(MediaType.APPLICATION_JSON)).andReturn();
		}

	}

}
