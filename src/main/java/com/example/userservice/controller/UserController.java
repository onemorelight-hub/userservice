package com.example.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;

@RestController
@RequestMapping (value = "userdetails")
public class UserController {


	@Autowired
	private UserService userService;
	
	@GetMapping (value = "/{userid}")
		public ResponseEntity<User> getUserDetails(@PathVariable String userid){
			User user = userService.getUserDetails(userid);
			if(user == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			return ResponseEntity.status(HttpStatus.FOUND).body(user);
		}
}
