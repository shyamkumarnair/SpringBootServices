package com.handson.user.exception;

public class InvalidCoordinateException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4227906373302266057L;

	public InvalidCoordinateException() {
		super(String.format("Invalid location coornates. Distance calculation failed"));
	}

}