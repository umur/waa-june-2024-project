package com.waa.project.security.service;

import com.waa.project.security.contract.AuthRequest;
import com.waa.project.security.contract.JwtAccessTokenResponse;
import com.waa.project.security.contract.JwtTokenResponse;
import com.waa.project.security.contract.RefreshTokenRequest;

public interface AuthService {
    JwtTokenResponse login(AuthRequest request);

    JwtAccessTokenResponse refreshToken(RefreshTokenRequest tokenRequest);
}
