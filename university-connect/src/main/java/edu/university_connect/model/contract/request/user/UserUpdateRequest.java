package edu.university_connect.model.contract.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserUpdateRequest {

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String username;

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    @Email(message = "should be a valid email ")
    private String email;

    private boolean enabled;
}
