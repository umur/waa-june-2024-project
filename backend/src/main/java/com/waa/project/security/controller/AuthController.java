package com.waa.project.security.controller;

import com.waa.project.security.contract.AuthRequest;
import com.waa.project.security.contract.JwtAccessTokenResponse;
import com.waa.project.security.contract.JwtTokenResponse;
import com.waa.project.security.contract.RefreshTokenRequest;
import com.waa.project.security.service.AuthService;
import com.waa.project.service.TokenBlacklistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthController(
            AuthService authService,
            TokenBlacklistService tokenBlacklistService) {
        this.authService = authService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<JwtAccessTokenResponse> refreshToken(@RequestBody RefreshTokenRequest tokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(tokenRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            tokenBlacklistService.addTokenToBlacklist(token);
        }
        return ResponseEntity.ok("Logged out successfully");
    }
}
