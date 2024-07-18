package edu.university_connect.model.contract.request.student;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentCreateRequest {

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String firstName;

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String lastName;

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    @Email(message = "should be a valid email ")
    private String email;

    @Min(1900)
    @Max(2100)
    private int year;

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String major;
}
