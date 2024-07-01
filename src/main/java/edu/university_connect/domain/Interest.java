package edu.university_connect.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Interest {
    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String interest;
}
