package com.example.userservice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.userservice.dao.UserDao;
import com.example.userservice.enumdef.LoginStatus;
import com.example.userservice.jwt.security.JwtTokenGenerator;
import com.example.userservice.model.LoginData;
import com.example.userservice.model.User;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserDao userDao;
		
	@Autowired
	private JwtTokenGenerator jwtToken;
	
	@Value("${application.invalidToken}")
	private String invalidToken;

	@Override
	public LoginData loginUser(User user) {
		LoginData loginData = new LoginData();
		User retrivedUser = userDao.getUserDetails(user.getEmail());
		// If user is not present 
		if(retrivedUser == null ) {
			loginData.setLoginStatus(LoginStatus.NOT_REGISTERED);
			return loginData;
		}
		// If user is not verified
		if(!retrivedUser.getVerified()) {
			loginData.setLoginStatus(LoginStatus.NOT_VERIFIED);
			return loginData;
		}
		// Verifying user password and 
		if(retrivedUser.getPassword().equals(user.getPassword())) {
			Date date = new Date();
			retrivedUser.setLastLogin(date);
			userDao.updateUser(retrivedUser);
			loginData.setLoginStatus(LoginStatus.LOGIN_SUCCESSED);
			loginData.setToken(jwtToken.generateToken(retrivedUser));
			return loginData;
		}else {
			loginData.setLoginStatus(LoginStatus.INVALID_CREDENTIAL);
			return loginData;		
		}
	}

	@Override
	public boolean signUpUser(User user) {
		 if(userDao.signUpUser(user)) {
		//	 sendEmail(user);
			 return true;
		 }
		 return false;
	}

	@Override
	public boolean verifyUser(String email, String verifyCode) {
		User retrivedUser = userDao.getUserDetails(email);
		if(retrivedUser != null) {
			if(retrivedUser.getVerifiedCode().equals(verifyCode)) {
				retrivedUser.setVerified(true);
				userDao.updateUser(retrivedUser);
				return true;
			}
		}
		return false;
	}
}
