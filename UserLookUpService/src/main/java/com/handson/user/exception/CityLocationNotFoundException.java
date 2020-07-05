package com.handson.user.exception;

public class CityLocationNotFoundException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8621238226490361694L;

	public CityLocationNotFoundException(String city) {
		super(String.format("Please check the city [ %s ] provided, it is not working ", city));
	}
}
