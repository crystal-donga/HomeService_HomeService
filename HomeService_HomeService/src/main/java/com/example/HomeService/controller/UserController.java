package com.example.HomeService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.HomeService.model.Users;
import com.example.HomeService.service.UserService;

@RestController
public class UserController {
	
    @Autowired
    UserService service;
    
	@PostMapping("/auth/register")
	public Users register(@RequestBody Users user) {
		return service.register(user);
	}
	
	@PostMapping("/auth/login")
	public String login(@RequestBody Users user) {
		return service.verify(user);
	}
}
