package com.example.HomeService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HomeService.model.Users;
import com.example.HomeService.model.role;
import com.example.HomeService.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	JWTservice jwtService;
	
	@Autowired
    AuthenticationManager authManager;
	
	@Autowired
	UserRepository repo;
    
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public Users register (Users user) {
		

		if (user.getRole() == null) {
	        user.setRole(role.USER); 
	    }
		 user.setPassword(encoder.encode(user.getPassword()));
	     repo.save(user);
		return user;
	}

	public String verify(Users user) {
		Authentication  authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
		if(authentication.isAuthenticated()) {
			String jwtToken = jwtService.generateToken(user.getEmail());
			return jwtToken;
		}else {
			return "Fail";
		}
	}

	

	
}
