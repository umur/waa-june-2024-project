package miu.waa.project1.dto.auth;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private TokenDTO tokenDTO;
    private Boolean isBlocked;
    private int loginAttempts;
    private LocalDateTime lockTime;
}
