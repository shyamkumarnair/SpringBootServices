package com.handson.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DistanceFormatAdvice {

	@ResponseBody
	@ExceptionHandler(DistanceFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String distanceFormatHandler(DistanceFormatException ex) {
		return ex.getMessage();
	}
}
