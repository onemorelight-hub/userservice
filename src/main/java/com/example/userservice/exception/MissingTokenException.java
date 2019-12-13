package com.example.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED , reason = "Incorrect or Missing")
public class MissingTokenException extends RuntimeException{

	private static final long serialVersionUID = -6347263958852856780L;

	public MissingTokenException() {
		super("Jwt Token is missing or Incorrect format");
	}
}
