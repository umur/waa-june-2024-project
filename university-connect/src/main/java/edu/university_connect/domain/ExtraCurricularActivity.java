package edu.university_connect.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExtraCurricularActivity {

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String activity;

    private String institute;

    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String accomplishment;
}
