/**
 * 
 */
package com.handson.user.util;

import org.springframework.stereotype.Service;

/**
 * @author PattathilS
 *
 */
@Service("DistanceCalculator")
public class CoordinateDistanceCalculator implements DistanceCalculator<Coordinate> {

	private static final double CONVERT_TO_MILES = 0.000621371;

	@Override
	public double findDistance(Coordinate start, Coordinate end) {

		double RADIUS = 6371e3; // earth radius in meter
		double lat1 = start.getLatitude();
		double lon1 = start.getLongitude();
		double lat2 = end.getLatitude();
		double lon2 = end.getLongitude();

		double a = (Math.sin(((lat2 - lat1) * Math.PI / 180) / 2) * Math.sin(((lat2 - lat1) * Math.PI / 180) / 2))
				+ ((Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180))
						* (Math.sin(((lon2 - lon1) * Math.PI / 180) / 2)
								* Math.sin(((lon2 - lon1) * Math.PI / 180) / 2)));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		double d = RADIUS * c;

		return d * CONVERT_TO_MILES;
	}
}
