package com.waa.project.security.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private final SecretKey          secretKey;
    private final long               expiration;
    private final long               refreshExpiration;
    private final UserDetailsService userDetailsService;

    public JwtTokenUtil(
            @Value("${spring.application.jwt.secret}") String secret,
            @Value("${spring.application.jwt.expiration:300}") Long expiration,
            @Value("${spring.application.jwt.refreshExpiration:3600}") Long refreshExpiration,
            UserDetailsService userDetailsService
                       ) {
        secret                  = Base64.getEncoder().encodeToString(secret.getBytes());
        this.expiration         = expiration;
        this.refreshExpiration  = refreshExpiration;
        this.userDetailsService = userDetailsService;

        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                                       .map(GrantedAuthority::getAuthority)
                                       .toList());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                   .claims(claims)
                   .subject(subject)
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis() + expiration * 1000))
                   .signWith(secretKey)
                   .compact();
    }

    public String generateRefreshToken(String subject) {
        return Jwts.builder()
                   .subject(subject)
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis() + refreshExpiration * 1000))
                   .signWith(secretKey)
                   .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                   .verifyWith(secretKey)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();


            return true;
        } catch (
                MalformedJwtException |
                ExpiredJwtException |
                UnsupportedJwtException |
                IllegalArgumentException e) {
            System.out.println("Invalid JWT Token: " + e.getMessage());
            return false;
        }
    }

    public String getSubject(String token) {
        return Jwts.parser()
                   .verifyWith(secretKey)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = getUserDetailsFromToken(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private UserDetails getUserDetailsFromToken(String token) {
        String username = getUsernameFromToken(token);
        return userDetailsService.loadUserByUsername(username);
    }
}
