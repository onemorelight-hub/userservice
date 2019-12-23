package com.example.userservice.jwt.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.userservice.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenValidator {

	@Value("${application.jwt.token.secret}")
	private String tokenSecret;
	
	  public User validate(String token) {

	    	System.out.println("JwtValidator.validate()");
	        User user = null;
	        try {
	            Claims body = Jwts.parser()
	                    .setSigningKey(tokenSecret)
	                    .parseClaimsJws(token)
	                    .getBody();

	            user = new User();

	            user.setFirstName((String)(body.get("userName")));
	            user.setEmail(body.get("userEmail").toString());
	            user.setUserType((String)(body.get("userType")));
	        }
	        catch (Exception e) {
	            System.out.println(e);
	        }
	        return user;
	    }
}
