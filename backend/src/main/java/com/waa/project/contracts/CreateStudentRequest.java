package com.waa.project.contracts;

import com.waa.project.enums.GenderType;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CreateStudentRequest {
    @NotNull(message = "Username cannot be null.")
    @NotBlank(message = "Username cannot be blank.")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters long.")
    private String username;

    @NotNull(message = "Password cannot be null.")
    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 8, max = 20)
    private String password;

    @NotNull(message = "First name cannot be null.")
    @NotBlank(message = "First name cannot be blank.")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters long.")
    private String firstName;

    @NotNull(message = "Last name cannot be null.")
    @NotBlank(message = "Last name cannot be blank.")
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters long.")
    private String lastName;

    @NotNull(message = "Email cannot be null.")
    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email must be a valid email.")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  birthDate;
    private GenderType genderType;

    @NotNull(message = "Student code cannot be null.")
    @NotBlank(message = "Student code cannot be blank.")
    @Size(min = 6, max = 12, message = "Student code must be between 6 and 12 character.")
    private String studentCode;

    @Pattern(regexp = "\\d*", message = "Academic years must only contain numbers.")
    private String academicYears;

    @NotNull(message = "Major Id cannot be null.")
    @Min(value = 1, message = "Major Id must be  positive.")
    private Long majorId;
}
