package com.example.userservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.model.LoginData;
import com.example.userservice.model.User;
import com.example.userservice.service.LoginService;

/**
 * This class is for Login Service Controller for sinup, login, and verify user 
 * @author anjan
 *
 */

@RestController
@RequestMapping (value ="/loginservice")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Value("${application.invalidToken}") 
	private String invalidToken;
	
	private static final String MESSAGE ="meaasge";
	
	/**
	 * This Method is for handling login requests 
	 * @param user : user login info
	 * @return
	 */
	@RequestMapping (value = "/login" , method = RequestMethod.POST , headers ="Accept=application/json")
	public ResponseEntity<Map<String, Object>> signIn(@RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		Map<String, Object> objectNode = new HashMap<String, Object>();
		LoginData loginData = loginService.loginUser(user);
		
		switch (loginData.getLoginStatus()) {
		case NOT_REGISTERED:
			objectNode.put(MESSAGE, "User is not registered");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(objectNode);
		case INVALID_CREDENTIAL:
			objectNode.put(MESSAGE, "Invalid Credential");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body(objectNode);
		case NOT_VERIFIED:
			objectNode.put(MESSAGE, "User is not verified");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body(objectNode);
		case LOGIN_SUCCESSED:
			headers.add("Authorisation", loginData.getToken());
			objectNode.put(MESSAGE, "Login sucessed");
			objectNode.put("Token", loginData.getToken());
			return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body(objectNode);
		default:
			objectNode.put(MESSAGE, "Something wrong went!");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(objectNode);
		}
	}
	/**
	 * This Method is to register new user request.
	 * @param user
	 * @return
	 */
	@PostMapping (value = "/signup")
	public ResponseEntity<Map<String, Object>> signUp(@RequestBody User user) {
		System.out.println("LoginController.signUp(): first_name: "+user.getFirstName());
		Map<String, Object> object = new HashMap<>();
		if(loginService.signUpUser(user)) {
			object.put(MESSAGE, "Sucess");
			return ResponseEntity.status(HttpStatus.OK).body(object);
		}else {
			object.put(MESSAGE, "User already registered");
			return ResponseEntity.status(HttpStatus.FOUND).body(object);
		}
	}
	/**
	 * This method verify a user details to activate user
	 * @param id
	 * @param verifyCode
	 * @return
	 */
	@GetMapping (value ="/verify/{id}/{verifyCode}")
	public ResponseEntity<Map<String, Object>> verfyUser(@PathVariable String id, @PathVariable String verifyCode) {
		Map<String, Object> object = new HashMap<>();
		if( loginService.verifyUser(id, verifyCode) ) {
			object.put(MESSAGE, "Sucess");
			return ResponseEntity.status(HttpStatus.OK).body(object);
		}else {
			object.put(MESSAGE, "Invalid request");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(object);
		}	
	}
}
