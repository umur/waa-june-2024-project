package edu.university_connect.service.impl;

import edu.university_connect.config.jwt.JwtTokenProvider;
import edu.university_connect.model.AppStatusCode;
import edu.university_connect.model.TokenType;
import edu.university_connect.model.contract.request.auth.RefreshTokenRequest;
import edu.university_connect.model.contract.response.common.AuthenticationResponse;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.model.SecurityUser;
import edu.university_connect.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${jwt_configs.access_token_validity_ms}")
    private long validityInMs;

    @Value("${jwt_configs.refresh_token_validity_ms}")
    private long refreshTokenValidityInMs;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsService userDetailsService;


    public AuthenticationResponse generateTokens(Authentication authentication) {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        String accessToken = jwtTokenProvider.createToken(authentication, TokenType.ACCESS, true);
        String refreshToken = jwtTokenProvider.createToken(authentication, TokenType.REFRESH, false);
        return new AuthenticationResponse(user.getUsername(),
                accessToken, refreshToken, (int) (validityInMs / 1000), (int) (refreshTokenValidityInMs / 1000));
    }

    public AuthenticationResponse authenticate(Authentication auth) {
            Authentication authentication = authenticationManager.authenticate(auth);
            return generateTokens(authentication);

    }

    public AuthenticationResponse authenticateRefreshToken(RefreshTokenRequest refreshTokenRequest) {
            Pair<String, String> data = jwtTokenProvider.getAuthentication(refreshTokenRequest.getRefreshToken());
            SecurityUser user = (SecurityUser) userDetailsService.loadUserByUsername(data.getFirst());
            if (user != null) {
                if (!user.isEnabled()) {
                    throw new DisabledException("account.not.active");
                }
                Authentication auth = new UsernamePasswordAuthenticationToken(user, data.getSecond(), user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
                return generateTokens(auth);
            } else {
                log.error("User associated with token not found");
                throw ServiceException.of(AppStatusCode.E40007);
            }

    }


}
