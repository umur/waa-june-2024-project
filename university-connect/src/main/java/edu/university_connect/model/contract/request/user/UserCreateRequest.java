package edu.university_connect.model.contract.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserCreateRequest {

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String username;

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    @Email(message = "should be a valid email ")
    private String email;

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,20}$", message = "Password must be 8-20 characters long and include at least one lowercase letter, one uppercase letter, one number, and one special character.")
    private String password;

    private boolean enabled;

}
