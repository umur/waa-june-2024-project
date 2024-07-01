package edu.university_connect.model.contract.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "should not be empty")
    @Email(message = "should be a valid email format")
    private String email;

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String username;

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,20}$", message = "Password must be 8-20 characters long and include at least one lowercase letter, one uppercase letter, one number, and one special character.")
    private String password;

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String confirmPassword;
}
