/**
 * 
 */
package com.handson.user.search;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.handson.user.exception.CityLocationNotFoundException;
import com.handson.user.json.Location;
import com.handson.user.json.User;
import com.handson.user.service.GeoCoordinateLocatorService;
import com.handson.user.util.Coordinate;
import com.handson.user.util.DistanceCalculator;

/**
 * @author PattathilS
 *
 */
@Service
public class UserSearchByArea {

	@Value("${source.users.url}")
	private String usersUrl;

	@Autowired
	private GeoCoordinateLocatorService coordinateService;

	@Autowired
	private DistanceCalculator<Coordinate> distanceCalculator;

	@Autowired
	private RestTemplate userRestServiceTemplate;// = new RestTemplate();

	private Location location = null;

	public List<User> search(String city, String distance) {
		location = findLocation(city).orElseThrow(() -> new CityLocationNotFoundException(city));
		Double searchDistance = Double.valueOf(distance);
		return Arrays.asList(userRestServiceTemplate.getForObject(usersUrl, User[].class)).stream()
				.filter(user -> (findDistance(user) <= searchDistance)).collect(Collectors.toList());
	}

	private Optional<Location> findLocation(String city) {
		return coordinateService.getLocaton(Optional.of(city));
	}

	private Double findDistance(User user) {
		return distanceCalculator.findDistance(new Coordinate(location.getLat(), location.getLng()),
				new Coordinate(user.getLatitude(), user.getLongitude()));
	}
}
