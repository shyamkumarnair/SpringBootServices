package com.handson.user.exception;

public class CityLocationNotFoundException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8621238226490361694L;

	public CityLocationNotFoundException(String city) {
		super(String.format(
				"Incorrect city, please check the city [ %s ] provided. example /users/city/<CityNameHere>/distance/<Distance here>/ ",
				city));
	}

	public CityLocationNotFoundException() {
		super(String.format(
				"Incorrect city, please check the city provided. example /users/city/<CityNameHere>/distance/<Distance here>/ "));
	}
}
