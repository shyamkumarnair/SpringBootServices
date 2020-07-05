package com.handson.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.handson.user.exception.CityLocationNotFoundException;
import com.handson.user.json.GeocodeResponse;
import com.handson.user.json.Geometry;
import com.handson.user.json.Location;
import com.handson.user.json.Result;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class GeoCoordinateLocatorServiceTest {

	@Mock
	private RestTemplate mockGeoLocationService;

	Fixture fixture = new Fixture();

	@InjectMocks
	private GeoCoordinateLocatorService geoCoordinateLocatorService = new GeoCoordinateLocatorService();

	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(geoCoordinateLocatorService, "findGeoLocationUrl", "testingUrl");
		ReflectionTestUtils.setField(geoCoordinateLocatorService, "defaultKey", "defaultKey");
	}

	@Test
	void testGetLocaton_withValidCity() {
		fixture.setValidResponse();
		fixture.whenGeoCoordinateServiceCalled();
		fixture.callService(Optional.of("Birmingham"));
		fixture.thenConfirmServiceCalled();
		fixture.thenConfirmValues();
	}

	@Test
	void testGetLocaton_withInvalidCity() {
		fixture.setInvalidResponse();
		fixture.whenGeoCoordinateServiceCalled();
		fixture.thenConfirmErrorThrown(Optional.of("wertrer"));
		fixture.thenConfirmServiceCalled();
	}

	@Test
	void testGetLocaton_withEmptyCity() {
		fixture.setInvalidResponse();
		fixture.whenGeoCoordinateServiceCalled();
		fixture.thenConfirmErrorThrown(Optional.of(""));
		fixture.thenConfirmServiceCalled();
	}

	@Test
	void testGetLocaton_withCityAsLondon() {
		fixture.setValidResponse();
		fixture.whenGeoCoordinateServiceCalled();
		fixture.callService(Optional.of("London"));
		fixture.thenConfirmServiceNotCalled();
		fixture.thenConfirmLondonValues();
	}

	private class Fixture {

		Optional<Location> location;
		GeocodeResponse geoResponse;

		public void setValidResponse() {
			Location location = new Location();
			location.setLat(1.1);
			location.setLng(2.2);
			Geometry geometry = new Geometry();
			geometry.setLocation(location);
			Result result = new Result();
			result.setGeometry(geometry);
			Result[] results = new Result[1];
			results[0] = result;
			geoResponse = new GeocodeResponse();
			geoResponse.setStatus("success");
			geoResponse.setResults(results);
		}

		public void thenConfirmLondonValues() {
			assertEquals(51.5073509, location.get().getLat());
			assertEquals(-0.1277583, location.get().getLng());
		}

		public void thenConfirmServiceNotCalled() {
			verify(mockGeoLocationService, never()).getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyString(),
					Mockito.anyString());
		}

		public void thenConfirmErrorThrown(Optional<String> city) {
			Assertions.assertThrows(CityLocationNotFoundException.class, () -> {
				geoCoordinateLocatorService.getLocaton(city);
			});
		}

		public void setInvalidResponse() {
			Result[] results = new Result[0];
			geoResponse = new GeocodeResponse();
			geoResponse.setStatus("ZERO_RESULTS");
			geoResponse.setResults(results);
		}

		public void whenGeoCoordinateServiceCalled() {
			when(mockGeoLocationService.getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyString(),
					Mockito.anyString())).thenReturn(geoResponse);
		}

		public void thenConfirmValues() {
			assertEquals(1.1, location.get().getLat());
			assertEquals(2.2, location.get().getLng());
		}

		public void thenConfirmServiceCalled() {
			verify(mockGeoLocationService).getForObject(Mockito.anyString(), Mockito.any(), Mockito.anyString(),
					Mockito.anyString());
		}

		public void callService(Optional<String> city) {
			location = geoCoordinateLocatorService.getLocaton(city);
		}
	}
}
