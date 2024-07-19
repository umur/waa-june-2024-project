package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import universityconnect.domain.User;
import universityconnect.domain.request.LoginRequest;
import universityconnect.domain.request.RefreshTokenRequest;
import universityconnect.domain.response.LoginResponse;
import universityconnect.repository.UserRepository;
import universityconnect.service.AuthService;
import universityconnect.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            logger.debug("Authenticating user with email: {}", loginRequest.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            logger.debug("User authenticated: {}", userDetails.getUsername());
            String accessToken = jwtUtil.generateToken(userDetails);
            String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());
            User user = userRepository.findByEmail(loginRequest.getEmail()).get();
            return new LoginResponse(accessToken, refreshToken, user.getRole().toString(), user.getId());
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials for user: {}", loginRequest.getEmail());
            throw new BadCredentialsException("Invalid credentials", e);
        }
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            String username = jwtUtil.extractUsername(refreshTokenRequest.getRefreshToken());
            String accessToken = jwtUtil.generateToken(userDetailsService.loadUserByUsername(username));
            User user = userRepository.findByEmail(username).get();
            return new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken(),user.getRole().toString(),user.getId());
        }
        throw new RuntimeException("Invalid refresh token");
    }

    @Override
    public void logout(String token) {
        try {
            String username = jwtUtil.extractUsername(token);
            logger.debug("Logging out user: {}", username);

            userRepository.findByEmail(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Invalidate the token
            // Note: This is a simple implementation. In a production environment,
            // you might want to add the token to a blacklist or implement a more
            // sophisticated token revocation mechanism.
            jwtUtil.invalidateToken(token);

            logger.info("User logged out successfully: {}", username);
        } catch (Exception e) {
            logger.error("Error during logout process", e);
            throw new RuntimeException("Logout failed", e);
        }
    }


}
