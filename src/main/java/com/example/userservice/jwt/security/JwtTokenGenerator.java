package com.example.userservice.jwt.security;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.userservice.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenGenerator {

	private String tokenIssuer = "One More Light";
	
	@Value("${application.jwt.token.secret}")
	private String tokenSecret;
	
	private String tokenSubject = "One More Light - access toke";
	
	 public String generateToken(User user) {
		 
	        Claims claims = Jwts.claims()
	                .setSubject(tokenSubject);
	        claims.put("userName", user.getFirstName());
	        claims.put("userEmail", user.getEmail());
	        claims.put("userType", user.getUserType());


	        return Jwts.builder()
	                .setClaims(claims)
	                .setIssuedAt(new Date())
	                .setIssuer(tokenIssuer)
	                .signWith(SignatureAlgorithm.HS512, tokenSecret)
	                .compact();
	    }
}
