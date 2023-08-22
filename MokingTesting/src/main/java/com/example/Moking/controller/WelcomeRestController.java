package com.example.Moking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Moking.service.WelcomeService;

@RestController
public class WelcomeRestController {
	
	@Autowired
	private WelcomeService welcomeService;
	
	@GetMapping("/welcome")
	public ResponseEntity<String> getMessage(){
		
		String message=welcomeService.getWelcomeMsg();
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}

}
