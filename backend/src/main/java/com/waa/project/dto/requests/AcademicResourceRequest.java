package com.waa.project.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicResourceRequest {
    @NotNull(message = "Name cannot be null.")
    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 2, max = 250, message = "Name must be minimum of 2 characters long.")
    private String name;

    @NotNull(message = "Description cannot be null.")
    @NotBlank(message = "Description cannot be blank.")
    @Size(min = 2, max = 250, message = "Description must be minimum of 2 characters long.")
    private String body;

    private File file;

    @NotNull(message = "Category cannot be empty.")
    private Long resourceId;
}
