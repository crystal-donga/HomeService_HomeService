package com.example.HomeService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.HomeService.model.Users;
import com.example.HomeService.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {
	
    @Autowired
    UserService service;
    
	@PostMapping("/auth/register")
	public Users register(@RequestBody Users user) {
		return service.register(user);
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody Users user,HttpServletResponse response) {
		return service.verify(user,response);
	}
	
	@GetMapping("/auth/AllData")
	public List<Users> getData() {
		System.out.println("hello");
		List<Users>users = service.getData();
		System.out.println(users);
		return users;
	
}
}
