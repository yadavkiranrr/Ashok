package com.Blog.App.BlogApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.App.BlogApp.payload.ApiResponse;
import com.Blog.App.BlogApp.payload.UserDto;
import com.Blog.App.BlogApp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	//Post-create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createUserDto=this.userService.createUser(userDto);
		
      return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		
	}
	
	
	//Put-update user
	@PutMapping("/{userid}")
	public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("userid") Integer id,@RequestBody UserDto userDto){
		UserDto updatedUser=this.userService.updateUser(id, userDto);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	
	//Delete-delete user
	@DeleteMapping("/{userid}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable ("userid" ) Integer userid){
		
		this .userService.deleteUser(userid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
		
	
	}
	
	
	//Get-get user
	@GetMapping("/{userid}")
	public ResponseEntity<UserDto> getById(@PathVariable ("userid") Integer id){
		
		UserDto user1=this.userService.getUserById(id);
		return new ResponseEntity<UserDto>(user1,HttpStatus.ACCEPTED);
		
	}
	
	//Get-getAll user
		@GetMapping("/all")
		public ResponseEntity<List<UserDto>> getAll(){
			
			List<UserDto> users=this.userService.AllUsers();
			return ResponseEntity.ok(users);
			
		}
	
	
}
