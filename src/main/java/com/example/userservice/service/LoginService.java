package com.example.userservice.service;

import org.springframework.stereotype.Service;

import com.example.userservice.model.LoginData;
import com.example.userservice.model.User;

@Service
public interface LoginService {
	public LoginData loginUser(String[] values);
	public boolean signUpUser(User user);
	public boolean verifyUser(String email, String verifyCode);
}
