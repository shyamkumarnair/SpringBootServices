package com.handson.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.handson.user.dao.UserSearchDao;
import com.handson.user.exception.CityLocationNotFoundException;
import com.handson.user.json.Response;
import com.handson.user.json.User;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class FindUserServiceTest {

	@Mock
	private UserSearchDao mockUerSearchDao;

	private Fixture fixture = new Fixture();

	@InjectMocks
	private FindUserService findUserService = new FindUserService();

	@Test
	void testGetUser_withValidCityandDistance() {
		fixture.createUserSearchByCityList();
		fixture.createUserSearchByCityAreaList();
		fixture.combineUserList();
		fixture.mockValues();
		fixture.createArgumentCaptures();
		fixture.callService("London", "50");
		fixture.verifyServiceCalls();
		fixture.verifyArguments("London", "50");
		fixture.verifyResult();
	}

	@Test
	void testGetUser_withInvalidCity() {
		fixture.createUserSearchByCityList();
		fixture.createUserSearchByCityAreaList();
		fixture.combineUserList();
		fixture.mockValues();
		fixture.mockCityLocationNotFoundError();
		fixture.createArgumentCaptures();
		fixture.callService("abc", "50");
		fixture.confirmServiceError();
	}

	@Test
	void testGetUser_withValidCityandInvalidDistance() {
		fixture.createUserSearchByCityList();
		fixture.createUserSearchByCityAreaList();
		fixture.combineUserList();
		fixture.mockValues();
		fixture.createArgumentCaptures();
		fixture.callService("London", "abc");
		fixture.verifyNoServiceCalls();
	}

	@Test
	void testGetUser_withValidCityAlone() {
		fixture.createUserSearchByCityList();
		fixture.createUserSearchByCityAreaList();
		fixture.combineUserList();
		fixture.mockValues();
		fixture.createArgumentCaptures();
		fixture.callService("London", null);
		fixture.verifyServiceCalls();
		fixture.verifyArguments("London", "0.0");
		fixture.verifyResult();
	}
	
	@Test
	void testGetUser_withEmptyCity() {
		fixture.createUserSearchByCityList();
		fixture.createUserSearchByCityAreaList();
		fixture.combineUserList();
		fixture.mockValues();
		fixture.createArgumentCaptures();
		fixture.callService("", "50");
		fixture.verifyNoServiceCalls();
		fixture.confirmServiceError();
	}

	private class Fixture {

		ArrayList<User> userListOne;
		ArrayList<User> userListTwo;
		ArgumentCaptor<String> cityArgument;
		ArgumentCaptor<String> distanceArgument;
		Response response;

		User userOne = new User(Long.parseLong("1"), "firstName", "lastName", "email@mail.com", "0.0.0.1", -0.1277583,
				51.5073509, "London");
		User userTwo = new User(Long.parseLong("2"), "firstName2", "lastName2", "email2@mail.com", "0.0.0.2",
				-0.1277583, 51.5073509, "Wembly");

		public void createUserSearchByCityList() {
			userListOne = new ArrayList<>();
			userListOne.add(userOne);
		}

		public void confirmServiceError() {
			assertTrue(HttpStatus.BAD_REQUEST.getReasonPhrase().equals(response.getStatus()));
		}

		public void createUserSearchByCityAreaList() {
			userListTwo = new ArrayList<>();
			userListTwo.add(userTwo);
		}

		public void verifyResult() {
			assertTrue(userListTwo.containsAll(response.getUsers()));
		}

		public void verifyArguments(String city, String distance) {
			assertEquals(city, cityArgument.getValue());
			assertEquals(distance, distanceArgument.getValue());
		}

		public void verifyServiceCalls() {
			verify(mockUerSearchDao).searchByCity(cityArgument.capture());
			verify(mockUerSearchDao).searchByCityArea(cityArgument.capture(), distanceArgument.capture());
		}

		public void verifyNoServiceCalls() {
			verify(mockUerSearchDao, never()).searchByCity(cityArgument.capture());
			verify(mockUerSearchDao, never()).searchByCityArea(cityArgument.capture(), distanceArgument.capture());
		}

		public void callService(String city, String distance) {
			response = findUserService.getUsers(city, distance);
		}

		public void createArgumentCaptures() {
			cityArgument = ArgumentCaptor.forClass(String.class);
			distanceArgument = ArgumentCaptor.forClass(String.class);
		}

		public void mockValues() {
			when(mockUerSearchDao.searchByCity(Mockito.anyString())).thenReturn(userListOne);
			when(mockUerSearchDao.searchByCityArea(Mockito.anyString(), Mockito.anyString())).thenReturn(userListTwo);
			when(mockUerSearchDao.searchById(Mockito.anyString())).thenReturn(userOne).thenReturn(userTwo);
		}

		public void mockCityLocationNotFoundError() {
			when(mockUerSearchDao.searchByCityArea(Mockito.anyString(), Mockito.anyString()))
					.thenThrow(new CityLocationNotFoundException());
		}

		public void combineUserList() {
			userListTwo.addAll(userListOne);
		}

	}
}
