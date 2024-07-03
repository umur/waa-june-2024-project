package com.waa.project.security.service.impl;

import com.waa.project.security.contract.AuthRequest;
import com.waa.project.security.contract.JwtAccessTokenResponse;
import com.waa.project.security.contract.JwtTokenResponse;
import com.waa.project.security.contract.RefreshTokenRequest;
import com.waa.project.security.exception.ExpiredTokenException;
import com.waa.project.security.service.AuthService;
import com.waa.project.security.util.JwtTokenUtil;
import com.waa.project.util.UserErrorMessages;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService    userDetailsService;
    private final JwtTokenUtil          jwtTokenUtil;

    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil
                          ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService    = userDetailsService;
        this.jwtTokenUtil          = jwtTokenUtil;
    }

    @Override
    public JwtTokenResponse login(AuthRequest request) {
        Authentication result = null;

        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException(UserErrorMessages.invalidCredentials());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());

        final String accessToken  = jwtTokenUtil.generateToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(request.getUsername());

        return new JwtTokenResponse(accessToken, refreshToken);
    }

    @Override
    public JwtAccessTokenResponse refreshToken(RefreshTokenRequest tokenRequest) {

        boolean isTokenValid = jwtTokenUtil.validateToken(tokenRequest.getRefreshToken());

        if (!isTokenValid) {
            throw new ExpiredTokenException(UserErrorMessages.tokenExpired());
        } else {

            final UserDetails userDetails = userDetailsService.loadUserByUsername(
                    jwtTokenUtil.getUsernameFromToken(tokenRequest.getRefreshToken()));

            final String accessToken = jwtTokenUtil.generateToken(userDetails);

            return new JwtAccessTokenResponse(accessToken);
        }
    }
}
