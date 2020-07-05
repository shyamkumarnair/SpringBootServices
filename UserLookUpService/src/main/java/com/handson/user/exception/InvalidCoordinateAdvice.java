package com.handson.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidCoordinateAdvice {

	@ResponseBody
	@ExceptionHandler(InvalidCoordinateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String InvalidCoordinateHandler(InvalidCoordinateException ex) {
		return ex.getMessage();
	}
}
