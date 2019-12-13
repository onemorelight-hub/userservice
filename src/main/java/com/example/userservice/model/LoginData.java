package com.example.userservice.model;

import com.example.userservice.enumdef.LoginStatus;

public class LoginData {

	private LoginStatus loginStatus;
	private String token;
	public LoginStatus getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
