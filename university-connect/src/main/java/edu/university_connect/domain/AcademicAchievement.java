package edu.university_connect.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AcademicAchievement {
    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String university;
    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String majors;
    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String level;
    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String score;

}
