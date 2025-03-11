package com.example.HomeService.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HomeService.model.Users;
import com.example.HomeService.model.role;
import com.example.HomeService.repo.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

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

	public ResponseEntity<?> verify(Users user, HttpServletResponse response) {
	    // Fetch user from database
	    Users dbUser = repo.findByEmail(user.getEmail());
	    System.out.println("userId" + " " + dbUser.getId());
	    
	    // Check if user exists
	    if (dbUser == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
	    }

	    // Check role mismatch
	    if (!user.getRole().equals(dbUser.getRole())) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid role");
	    }

	    try {
	        // Authenticate user
	        Authentication authentication = authManager.authenticate(
	            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
	        );

	        if (authentication.isAuthenticated()) {
	            // Generate JWT token
	            String jwtToken = jwtService.generateToken(dbUser.getId(),user.getEmail(),user.getRole());

	            // Set JWT token in HttpOnly cookie
	            ResponseCookie cookie = ResponseCookie.from("authToken", jwtToken)
	                .httpOnly(true) // Prevent JavaScript access
	                .secure(false) // Set true in production
	                .path("/")
	                .sameSite("Lax")
	                .build();
	            
                System.out.println(cookie);
	            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

	            // Return user details (without password)
	            Map<String, Object> responseBody = new HashMap<>();
	            responseBody.put("message", "Login successful");
	            responseBody.put("user", dbUser); // Consider using DTO to avoid exposing sensitive data
	            responseBody.put("token",cookie.toString());
	            return ResponseEntity.ok(responseBody);
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }

	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
	}


	public List<Users> getData() {
		System.out.println("users data");
		return repo.findAll();
	}

	

	
}
