package com.example.userservice.jwt.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken{
	private static final long serialVersionUID = 1L;
	private String token;
	
	public JwtAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public JwtAuthenticationToken(String token) {
		super(null, null);
        this.token = token;
        System.out.println("JwtAuthenticationToken.JwtAuthenticationToken(): Constructor called");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
