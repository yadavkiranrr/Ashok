package com.Blog.App.BlogApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.App.BlogApp.payload.JwtAuthRequest;
import com.Blog.App.BlogApp.security.JwtAuthResponse;
import com.Blog.App.BlogApp.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager auhenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
		@RequestBody JwtAuthRequest request)
	{
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
		this.authenticate(request.getUsername(),request.getPassword());
		
		String token=this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}
	
	private void authenticate(String username,String password) {
		
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		
	
		this.auhenticationManager.authenticate(authenticationToken);
	}

}
