package com.handson.user.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.handson.user.dao.UserSearchDao;
import com.handson.user.exception.DistanceFormatException;
import com.handson.user.exception.InvalidCityException;
import com.handson.user.json.Response;
import com.handson.user.json.User;

@Service
public class FindUserService {

	private static final String DECIMAL_FORMAT_REGEX = "^[0-9]\\d*(\\.\\d+)?$";

	@Autowired
	private UserSearchDao userSearchDao;

	private static final Logger log = LoggerFactory.getLogger(FindUserService.class);

	public Response getUsers(String city, String distance) {
		Response response = new Response();
		distance = StringUtils.isBlank(distance) ? "0.0" : distance;
		RuntimeException ex = validateParameters(city, distance);
		if (ex != null) {
			populateError(response, ex);
			return response;
		}
		response.setStatus(HttpStatus.OK.name());
		response.setMessage("success");
		try {
			List<User> usersResultList = Stream
					.concat(userSearchDao.searchByCity(city).stream(),
							userSearchDao.searchByCityArea(city, distance).stream())
					.distinct().map(user -> userSearchDao.searchById(String.valueOf(user.getId())))
					.collect(Collectors.toList());
			log.info("size list - " + usersResultList.size());
			response.setUsers(usersResultList);
		} catch (Exception e) {
			log.info(String.format("Error happend while searching with city [ %s] and distance [%s] ", city, distance));
			log.error("Error happened ", e);
			response.setMessage(
					"Search failed ! Please check the parameters provided. Example /users/city/<CityNameHere>/distance/<Distance here>/ ");
			response.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		}
		return response;
	}

	private void populateError(Response response, RuntimeException ex) {
		response.setMessage(ex.getLocalizedMessage());
		response.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		response.setUsers(Arrays.asList());
	}

	private RuntimeException validateParameters(String city, String distance) {
		if (!distance.matches(DECIMAL_FORMAT_REGEX)) {
			return new DistanceFormatException(distance);
		}
		if (StringUtils.isBlank(city)) {
			return new InvalidCityException(city);
		}
		return null;
	}
}
