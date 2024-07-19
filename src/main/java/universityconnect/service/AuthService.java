package universityconnect.service;

import universityconnect.domain.request.LoginRequest;
import universityconnect.domain.request.RefreshTokenRequest;
import universityconnect.domain.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    void logout(String token);

}
