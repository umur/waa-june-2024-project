package edu.university_connect.model.contract.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable {

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String username;

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String password;
}