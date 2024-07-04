package edu.university_connect.service.auth;

import edu.university_connect.config.jwt.JwtTokenProvider;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.model.contract.dto.UserDto;
import edu.university_connect.model.contract.request.auth.SignUpRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.model.SecurityUser;
import edu.university_connect.model.enums.TokenType;
import edu.university_connect.model.contract.request.auth.RefreshTokenRequest;
import edu.university_connect.model.contract.response.AuthenticationResponse;
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
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${jwt_configs.access_token_validity_s}")
    private long validity;

    @Value("${jwt_configs.refresh_token_validity_s}")
    private long refreshTokenValidity;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsService userDetailsService;


    public AuthenticationResponse generateTokens(Authentication authentication) {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        String accessToken = jwtTokenProvider.createToken(authentication, TokenType.ACCESS, true);
        String refreshToken = jwtTokenProvider.createToken(authentication, TokenType.REFRESH, false);
        return new AuthenticationResponse(user.getUsername(),
                accessToken, refreshToken, (int) (validity), (int) (refreshTokenValidity));
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
