package com.handson.user.service;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.handson.user.dao.UserSearchDao;
import com.handson.user.exception.DistanceFormatException;
import com.handson.user.exception.InvalidCityException;
import com.handson.user.json.Response;

@Service
public class FindUserService {

	private static final String DECIMAL_FORMAT_REGEX = "^[0-9]\\d*(\\.\\d+)?$";

	@Autowired
	private UserSearchDao userSearchDao;

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
		response.setUsers(Stream
				.concat(userSearchDao.searchByCity(city).parallelStream(),
						userSearchDao.searchByCityArea(city, distance).parallelStream())
				.parallel().distinct().map(user -> userSearchDao.searchById(String.valueOf(user.getId())))
				.collect(Collectors.toList()));
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
