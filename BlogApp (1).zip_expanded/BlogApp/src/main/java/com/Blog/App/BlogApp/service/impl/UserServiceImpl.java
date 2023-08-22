package com.Blog.App.BlogApp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.App.BlogApp.entity.User;
import com.Blog.App.BlogApp.exception.ResourceNotFoundException;
import com.Blog.App.BlogApp.payload.UserDto;
import com.Blog.App.BlogApp.repository.UserRepo;
import com.Blog.App.BlogApp.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user1=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user1);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(Integer id, UserDto userDto) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		 user.setId(userDto.getId());
		 user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());;
       user.setPassword(userDto.getPassword());
   	     user.setAbout(userDto.getAbout());
       User updateUser=this.userRepo.save(user);
       
       UserDto userDto1=this.userToDto(updateUser);
       
		return userDto1;
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
	User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id" ,id));
	this.userRepo.delete(user);
	}

	@Override
	public UserDto getUserById(Integer id) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("user","id",id));
		
		return userToDto(user);
	
	}

	@Override
	public List<UserDto> AllUsers() {
		// TODO Auto-generated method stub
		List<User> users=this.userRepo.findAll();
		
		List<UserDto> userDtos=users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}
	
	public User dtoToUser(UserDto userDto) {
//		
		User user=this.modelMapper.map(userDto,User.class);
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	public UserDto userToDto(User user) {
		
		UserDto userDto=this.modelMapper.map(user,UserDto.class);
		
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//		
		return userDto;
	}
	

}
