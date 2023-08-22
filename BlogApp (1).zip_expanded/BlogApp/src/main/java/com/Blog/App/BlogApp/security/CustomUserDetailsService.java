package com.Blog.App.BlogApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Blog.App.BlogApp.entity.User;
import com.Blog.App.BlogApp.exception.ResourceNotFoundException;
import com.Blog.App.BlogApp.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("username", "email"+username, 0));
		return user;
	}

	// loading user fromdatabases;
	
}
