package com.example.userservice.jwt.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.example.userservice.exception.MissingTokenException;
import com.example.userservice.jwt.model.JwtAuthenticationToken;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationTokenFilter() {
        super("/userdetails/**");
    	System.out.println("JwtAuthenticationTokenFilter.JwtAuthenticationTokenFilter(); Contructor called");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        String header = httpServletRequest.getHeader("Authorisation");
        String header1 = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String header2 = httpServletRequest.getHeader("Authorization");
        String header3 = httpServletRequest.getHeader("test");

		System.out.println("JwtAuthenticationTokenFilter.attemptAuthentication()-> header: "+header);
		System.out.println("JwtAuthenticationTokenFilter.attemptAuthentication()-> header1: "+header1);
		System.out.println("JwtAuthenticationTokenFilter.attemptAuthentication()-> header2: "+header2);
		System.out.println("JwtAuthenticationTokenFilter.attemptAuthentication()-> header3: "+header3);

        if (header == null || !header.startsWith("Token ")) {
            throw new MissingTokenException();
        }

        String authenticationToken = header.substring(6);
        System.out.println("JwtAuthenticationTokenFilter.attemptAuthentication() authenticationToken: "+authenticationToken);
        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        System.out.println("JwtAuthenticationTokenFilter.successfulAuthentication()");
        chain.doFilter(request, response);
    }
}
