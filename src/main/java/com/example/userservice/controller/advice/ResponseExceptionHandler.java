//package com.example.userservice.controller.advice;
//
//import java.util.Date;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import com.example.userservice.exception.ErrorDetails;
//import com.example.userservice.exception.InvalidTokenException;
//import com.example.userservice.exception.MissingTokenException;
//
//@ControllerAdvice(basePackages ="com.example.userservice")
//@RestController
//public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
//	
//	
//	@ExceptionHandler(InvalidTokenException.class)
//	@ResponseStatus(value = HttpStatus.UNAUTHORIZED , reason = "Invalid or Missing")
//	public final ResponseEntity<ErrorDetails> handleInvalidTokenException(InvalidTokenException ex, WebRequest request){
//	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
//	    return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
//	}
//	
//	@ExceptionHandler(MissingTokenException.class)
//	@ResponseStatus(value = HttpStatus.UNAUTHORIZED , reason = "Invalid or Missing")
//	public final ResponseEntity<ErrorDetails> handleMissingTokenException(MissingTokenException ex, WebRequest request){
//	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
//	    return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
//	}
//	
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR , reason = "Something went wrong")
//	  public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
//	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
//	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//	  }
//}
