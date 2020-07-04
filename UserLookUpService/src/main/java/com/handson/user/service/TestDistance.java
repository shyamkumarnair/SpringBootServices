package com.handson.user.service;

public class TestDistance {

	public static void main(String args[]) {
		double R = 6371e3; // earth radius in meter

		/*double lat1 = -6.5115909;
		double lon1 = 105.652983;*/
		
		double lat1 = 51.6710832;
		double lon1 = 0.8078532;

		/*
		 * double lat1 = 52.633331; double lon1 = -1.133333;
		 */
		double lat2 = 51.509865;
		double lon2 = -0.118092;
		/*double lat2 = -6.7098551;
		double lon2 = 111.3479498;*/

		// double distance = (Math.sin(lat1/57.2958) * Math.sin(lat2/57.2958)) +
		// (Math.cos(lat1/57.2958) * Math.cos(lat2/57.2958) * Math.cos(lon2/57.2958 -
		// lon1/57.2958));
		double a = (Math.sin(((lat2 - lat1) * Math.PI / 180) / 2) * Math.sin(((lat2 - lat1) * Math.PI / 180) / 2))
				+ ((Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180))
						* (Math.sin(((lon2 - lon1) * Math.PI / 180) / 2)
								* Math.sin(((lon2 - lon1) * Math.PI / 180) / 2)));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		double d = R * c;

		double distanceInKm = d * 0.000621371;

		System.out.println("Distance - " + (int) Math.round(distanceInKm));
	}

}
