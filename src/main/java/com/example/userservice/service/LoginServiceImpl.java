package com.example.userservice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.userservice.dao.UserDao;
import com.example.userservice.enumdef.LoginStatus;
import com.example.userservice.jwt.security.JwtTokenGenerator;
import com.example.userservice.model.LoginData;
import com.example.userservice.model.MailInfo;
import com.example.userservice.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserDao userDao;
		
	@Autowired
	private JwtTokenGenerator jwtToken;
	
	@Value("${application.invalidToken}")
	private String invalidToken;
	
	private String mailServiceUrl = "http://mail-service:2021";

	@Override
	public LoginData loginUser(String[] values) {
		LoginData loginData = new LoginData();
		User retrivedUser = userDao.getUserDetails(values[0]);
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
		if(retrivedUser.getPassword().equals(values[1])) {
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
		boolean status = false;
		 if(userDao.signUpUser(user)) {
			 MailInfo mailInfo = new MailInfo();
			 String[] receiverList = new String[1];
			 receiverList[0] = user.getEmail();
			 mailInfo.setReceiverList(receiverList);
			 mailInfo.setSubject("Verification Mail:-> One More Light");
			 String message = "Please click the link to verify: " + "https://localhost:8443/" +user.getEmail() +"/" + user.getVerificationCode();
			 mailInfo.setMessage(message);
			 ObjectMapper mapper = new ObjectMapper();
			 String request = null;
			 try {
				request = mapper.writeValueAsString(mailInfo);
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}
			 try {
				 System.out.println("Test-> request body: "+request);
				 HttpResponse<JsonNode> jsonResponse = Unirest.post(mailServiceUrl + "/sendmail/sendVerificationMail")
						 .header("accept", "application/json")
					      .header("content-type", "application/json")
						 .body(request)
						 .asJson();
				 if(jsonResponse.getStatus() == 200) {
					 status = true;
				 }
			} catch (UnirestException e) {
				e.printStackTrace();
			}
		 }
		 return status;
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
