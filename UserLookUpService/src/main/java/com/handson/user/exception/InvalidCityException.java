package com.handson.user.exception;

public class InvalidCityException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6477967414309515764L;

	public InvalidCityException(String city) {
		super(String.format("Invalid city [%s] value. Please check your value again ", city));
	}
	
	public InvalidCityException() {
		super(String.format("Empty city value provided. City is required for search" ));
	}
}
