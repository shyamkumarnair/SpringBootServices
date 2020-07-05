package com.handson.user.exception;

public class DistanceFormatException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public DistanceFormatException(String distance) {
		super(String.format(
				"Please check the format of distance parameter provided - [%s], it is not working. It needs to be like 50 or 50.000 ",
				distance));
	}
}
