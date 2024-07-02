package com.waa.project.security.controller;

import com.waa.project.security.contract.AuthRequest;
import com.waa.project.security.contract.JwtAccessTokenResponse;
import com.waa.project.security.contract.JwtTokenResponse;
import com.waa.project.security.contract.RefreshTokenRequest;
import com.waa.project.security.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(
            AuthService authService
                         ) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<JwtAccessTokenResponse> refreshToken(@RequestBody RefreshTokenRequest tokenRequest) {
        return ResponseEntity.ok(authService.refreshToken(tokenRequest));
    }
}
