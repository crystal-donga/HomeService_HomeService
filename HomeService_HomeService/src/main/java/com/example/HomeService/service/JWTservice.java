package com.example.HomeService.service;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTservice {
	@Value("${jwt.secret}")
    private String secretKey;
	 
	public String generateToken(String email) {
		Map<String,Object>claims = new HashMap<>();
		
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(email)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60*60*30))
				.and()
				.signWith(getKey())
				.compact();
	}

	private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
