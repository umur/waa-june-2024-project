package miu.waa.project1.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import miu.waa.project1.common.Role;
import miu.waa.project1.common.TokenType;
import miu.waa.project1.dto.auth.AuthResponseDTO;
import miu.waa.project1.dto.auth.LoginDTO;
import miu.waa.project1.dto.auth.SignUpDTO;
import miu.waa.project1.dto.auth.TokenDTO;
import miu.waa.project1.model.Token;
import miu.waa.project1.model.User;
import miu.waa.project1.repository.TokenRepository;
import miu.waa.project1.repository.UserRepository;
import miu.waa.project1.service.AuthService;
import miu.waa.project1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    // Config in application.properties
    @Value("${block.attempts.login.minutes}")
    private int blockMinutes;

    public TokenDTO register(SignUpDTO request) {
        Boolean emailExisted = userRepository.existsByEmail(request.getEmail());
        if (emailExisted) return null;

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(Role.STUDENT.name()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        // Generate token and save token
        return generateAndSaveAndResponseTokens(user);
    }

    public AuthResponseDTO signIn(LoginDTO request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) return null;

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return handleIncorrectPassword(user);
        }

        if (user.getLockTime() != null && user.getLockTime().isAfter(LocalDateTime.now())) {
            return createAuthResponse(true, null, user.getLoginAttempts(), user.getLockTime());
        }

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        user.setLoginAttempts(0);
        user.setLockTime(null);
        userRepository.save(user);

        // Generate token and save token
        TokenDTO token = generateAndSaveAndResponseTokens(user);
        return createAuthResponse(false, token, 0, null);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token userToken = new Token();
        userToken.setUser(user);
        userToken.setToken(jwtToken);
        userToken.setTokenType(TokenType.BEARER);
        userToken.setExpired(false);
        userToken.setRevoked(false);
        tokenRepository.save(userToken);
    }

     private TokenDTO generateAndSaveAndResponseTokens(User user) {
        String jwtToken = jwtUtil.generateToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        TokenDTO authResponse = new TokenDTO();
        authResponse.setAccessToken(jwtToken);
        authResponse.setRefreshToken(refreshToken);
        return authResponse;
    }

    private AuthResponseDTO handleIncorrectPassword(User user) {
        if (user.getLoginAttempts() < 5) {
            return incrementLoginAttempts(user);
        } else {
            return handleBlockedUser(user);
        }
    }

    private AuthResponseDTO incrementLoginAttempts(User user) {
        int numberLoginAttempts = user.getLoginAttempts() + 1;
        user.setLoginAttempts(numberLoginAttempts);
        if (numberLoginAttempts >= 5) user.setLockTime(LocalDateTime.now().plusMinutes(blockMinutes));
        userRepository.save(user);
        return createAuthResponse(numberLoginAttempts == 5, null, numberLoginAttempts, user.getLockTime());
    }

    private AuthResponseDTO handleBlockedUser(User user) {
        if (user.getLockTime() == null) {
            user.setLockTime(LocalDateTime.now().plusMinutes(blockMinutes));
            userRepository.save(user);
        }
        if (user.getLockTime().isAfter(LocalDateTime.now())) {
            return createAuthResponse(true, null, user.getLoginAttempts(), user.getLockTime());
        } else {
            user.setLoginAttempts(1);
            user.setLockTime(null);
            userRepository.save(user);
            return createAuthResponse(false, null, 1, null);
        }
    }

    private AuthResponseDTO createAuthResponse(boolean isBlocked, TokenDTO token, int loginAttempts, LocalDateTime lockTime) {
        AuthResponseDTO authResponse = new AuthResponseDTO();
        authResponse.setIsBlocked(isBlocked);
        authResponse.setTokenDTO(token);
        authResponse.setLoginAttempts(loginAttempts);
        authResponse.setLockTime(lockTime);
        return authResponse;
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtUtil.extractUsername(refreshToken);
        if (userEmail == null) throw new UsernameNotFoundException("User not found!");
        User user = userRepository.findByEmail(userEmail);
        if (user == null) throw new UsernameNotFoundException("User not found!");
        if (jwtUtil.isTokenValid(refreshToken, user)) {
            String accessToken = jwtUtil.generateToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, accessToken);
            TokenDTO authResponse = new TokenDTO();
            authResponse.setAccessToken(accessToken);
            authResponse.setRefreshToken(refreshToken);
            new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
        }
    }
}
