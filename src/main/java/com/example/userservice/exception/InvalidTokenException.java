package com.example.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED , reason = "Invalid or Missing")
public class InvalidTokenException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private static final String message = "JwT Token is incorrect";
	public InvalidTokenException() {
		super(message);
	}
}
