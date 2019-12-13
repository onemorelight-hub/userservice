package com.example.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userservice.dao.UserDao;
import com.example.userservice.model.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	@Override
	public User getUserDetails(String userid) {
		return userDao.getUserDetails(userid);
	}

}
