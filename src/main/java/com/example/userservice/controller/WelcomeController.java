package com.example.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping  (value = "/welcome")
public class WelcomeController {

	@GetMapping (value = "/test")
	public String getWelcome() {
		return "One More Light";
	}
}
