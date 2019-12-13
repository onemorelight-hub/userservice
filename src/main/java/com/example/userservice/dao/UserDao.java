package com.example.userservice.dao;

import org.springframework.stereotype.Repository;

import com.example.userservice.model.User;

@Repository
public interface UserDao {
	public boolean signUpUser(User user);
	public boolean updateUser(User user);
	public User getUserDetails(String email);
}