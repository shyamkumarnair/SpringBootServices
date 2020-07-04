package com.handson.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.handson.user.json.Location;
import com.handson.user.json.User;
import com.handson.user.util.Coordinate;
import com.handson.user.util.DistanceCalculator;

@Service
public class FindUserService {

	private RestTemplate userRestServiceTemplate = new RestTemplate();

	@Autowired
	private GeoCoordinateLocatorService coordinateService;

	@Autowired
	private DistanceCalculator<Coordinate> distanceCalculator;
	
	@Value("${source.users.url}")
	private String usersUrl;
	
	@Value("${source.users.byid.url}")
	private String userByIdUrl;
	
	@Value("${source.users.bycity.url}")
	private String userByCityUrl;

	/*
	 * @Autowired public FindUserService(RestTemplateBuilder builder) {
	 * userRestServiceTemplate = builder.build(); }
	 */
	// @HystrixCommand(fallbackMethod = "getFallbackUser")
	public List<User> getUsers(String city, String distance) {

		Double searchDistance = Double.valueOf(distance);
		BiPredicate<User, Location> biPred = (user, location) -> findDistance(user, location) <= searchDistance;
		Optional<Location> location = findLocation(city);
		List<User> users = Stream.concat(
				Arrays.asList(userRestServiceTemplate.getForObject(userByCityUrl, User[].class, city)).parallelStream(),
				Arrays.asList(userRestServiceTemplate.getForObject(usersUrl, User[].class)).parallelStream()
						.filter(user -> biPred.test(user, location.get())))
				.parallel().distinct()
				.map(user -> userRestServiceTemplate.getForObject(userByIdUrl, User.class, user.getId()))
				.collect(Collectors.toList());

		return users;
	}

	private Optional<Location> findLocation(String city) {
		return coordinateService.getLocaton(Optional.of(city), Optional.empty());
	}

	public Double findDistance(User user, Location location) {
		return distanceCalculator.findDistance(new Coordinate(location.getLat(), location.getLng()),
				new Coordinate(user.getLatitude(), user.getLongitude()));
	}

	public List<User> getFallbackUsers() {
		// Read from cache and populate
		return new ArrayList<User>();
	}


}
