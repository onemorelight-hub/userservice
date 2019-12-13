package com.example.userservice.service;

import org.springframework.stereotype.Service;

import com.example.userservice.model.User;

@Service
public interface UserService {
		public User getUserDetails(String userid);
}
