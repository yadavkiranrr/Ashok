package com.Blog.App.BlogApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Blog.App.BlogApp.payload.UserDto;

@Service
public interface UserService  {
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(Integer id ,UserDto user);
	
	void deleteUser(Integer id);
	
	UserDto getUserById(Integer id);
	
	List<UserDto> AllUsers();

}
