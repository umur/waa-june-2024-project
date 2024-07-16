package universityconnect.domain.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String role;
    private Long userId;

    public LoginResponse(String accessToken, String refreshToken, String role,Long userId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.role = role;
        this.userId = userId;
    }

    public LoginResponse() {}
}
