package com.example.HomeService.service;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
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
				.expiration(new Date(System.currentTimeMillis() + 60*60*60*30))
				.and()
				.signWith(getKey())
				.compact();
	}

	private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
	public String extractEmail(String token) {
		   return extractClaim(token, Claims::getSubject);
	}

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
	        return Jwts.parser()
	                .verifyWith((SecretKey) getKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	}



	private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String email = extractEmail(token);
		System.out.println("true");
		System.out.println((email.equals(userDetails.getUsername()) && !isTokenExpired(token)));
		return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

		
}
