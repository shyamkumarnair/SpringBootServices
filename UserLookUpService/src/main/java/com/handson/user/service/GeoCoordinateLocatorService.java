package com.handson.user.service;

import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.handson.user.exception.CityLocationNotFoundException;
import com.handson.user.exception.InvalidCityException;
import com.handson.user.json.GeocodeResponse;
import com.handson.user.json.Location;
import com.handson.user.json.Result;

/**
 * Helps to find the geo-coordinates (latitude and longitude) of a city
 * 
 * @author PattathilS
 *
 */
@Service
public class GeoCoordinateLocatorService {

	private static final double LONDON_LON = -0.1277583;

	private static final double LONDON_LAT = 51.5073509;

	@Value("${geolocation.finder.url}")
	private String findGeoLocationUrl;

	@Value("${geolocation.finder.default.key}")
	private String defaultKey;

	@Value("${geolocation.finder.default.city}")
	private String defaultCity;

	@Autowired
	private RestTemplate geoLocationService;

	private static final Logger log = LoggerFactory.getLogger(GeoCoordinateLocatorService.class);

	// configure Hystrix for fault tolerance
	public Optional<Location> getLocaton(Optional<String> city) {

		Optional<String> key = Optional.empty();
		log.info("getLocation invoked- city [{}] and key [{}]", city.orElse(defaultCity),
				key.isPresent() ? "key" : "defaultkey");

		if ("London".equalsIgnoreCase(city.orElse(""))) {
			return Optional.of(new Location(LONDON_LAT, LONDON_LON));
		}

		Result[] results = geoLocationService.getForObject(findGeoLocationUrl, GeocodeResponse.class,
				city.orElseThrow(() -> new InvalidCityException()), key.orElse(defaultKey)).getResults();

		if (results.length <= 0) {
			throw new CityLocationNotFoundException(city.get());
		}

		Optional<Location> location = Arrays.asList(results).stream().map(result -> result.getGeometry().getLocation())
				.findFirst();

		log.info("getLocation call to find co-ordinates : [{}]", location.isPresent() ? location.get() : "failed");

		return location;
	}

}
