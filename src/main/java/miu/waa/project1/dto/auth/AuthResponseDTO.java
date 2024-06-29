package miu.waa.project1.dto.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthResponseDTO {
    private TokenDTO tokenDTO;
    private Boolean isBlocked;
    private int loginAttempts;
    private LocalDateTime lockTime;
}
