package com.handson.user.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.handson.user.exception.InvalidCoordinateException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class CoordinateDistanceCalculatorTest {

	private static final double LONDON_LON = -0.1277583;
	private static final double LONDON_LAT = 51.5073509;
	private static final double LEC_LAT = 52.633331;
	private static final double LEC_LON = -1.133333;

	@InjectMocks
	CoordinateDistanceCalculator coordinateDistanceCalculator;// = new CoordinateDistanceCalculator();

	@Test
	void testFindDistance_WithValidCordinates() {
		fixture.setCoordinates();
		fixture.callFindDistance();
		fixture.confirmResult();
	}

	@Test
	void testFindDistance_WithMissingCordinates() {
		fixture.setMissingCoordinates();
		fixture.callFindDistanceWithMissingCordinates();
	}

	Fixture fixture = new Fixture();

	class Fixture {
		Coordinate startCordinate;
		Coordinate endCordinate;

		double result;

		public void setCoordinates() {
			startCordinate = new Coordinate(LONDON_LAT, LONDON_LON);
			endCordinate = new Coordinate(LEC_LAT, LEC_LON);
		}

		public void callFindDistanceWithMissingCordinates() {
			Assertions.assertThrows(InvalidCoordinateException.class, () -> {
				coordinateDistanceCalculator.findDistance(startCordinate, endCordinate);
			});
		}

		public void setMissingCoordinates() {
			startCordinate = new Coordinate(null, LONDON_LON);
			endCordinate = new Coordinate(LEC_LAT, null);
		}

		public void confirmResult() {
			assertEquals(88.74724157859704, result);
		}

		public void callFindDistance() {
			result = coordinateDistanceCalculator.findDistance(startCordinate, endCordinate);
		}

	}
}
