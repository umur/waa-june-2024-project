package com.waa.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MajorDto {
    @NotNull(message = "Name cannot be null.")
    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 2, max = 30, message = "Name must be between 3 and 30 characters long.")
    private String name;
    @NotNull(message = "Description cannot be null.")
    @NotBlank(message = "Description cannot be blank.")
    private String description;
}
