package edu.university_connect.model.contract.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private String username;
    private String accessToken;
    private String refreshToken;
    private int accessTokenExpiry;
    private int refreshTokenExpiry;
}
