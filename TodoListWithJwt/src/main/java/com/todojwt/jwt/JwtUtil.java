package com.todojwt.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtUtil {
	
		
	private final String SECRET="kritisharmatitin264653829929082736465645";
	
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}
	
	public String extractUsername(String token) {
		Claims claims=extractAllClaims(token);
		return claims.getSubject();
	}
	
	public Date extractExpiration(String token) {
		return (Date) extractAllClaims(token).getExpiration();
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
	}	
	
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpire(token));
    }
	
	private boolean isTokenExpire(String token) {
		return extractExpiration(token).before(new java.util.Date());
	}
	public String generateToken(String username) {
		 Map<String, Object> claims = new HashMap<>();
		 claims.put("role","USER");
	        return createToken(claims, username);
		
	}
	
	  private String createToken(Map<String, Object> claims, String subject) {
	        return Jwts.builder()
	                .claims(claims)
	                .subject(subject)
	                .header().empty().add("typ","JWT")
	                .and()
	                .issuedAt(new Date(System.currentTimeMillis()))
	                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
	                .signWith(getSigningKey())
	                .compact();
	    }
	  
	  public Boolean validateToken(String token) {
	        return !isTokenExpire(token);
	    }
	
}
