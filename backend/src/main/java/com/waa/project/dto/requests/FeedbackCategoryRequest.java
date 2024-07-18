package com.waa.project.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FeedbackCategoryRequest {
    @NotNull(message = "Category name cannot be null.")
    @NotBlank(message = "Category name cannot be blank.")
    @Size(min = 2, max = 250, message = "Category name must be minimum of 2 characters long.")
    private String name;

    @NotNull(message = "Description cannot be null.")
    @NotBlank(message = "Description cannot be blank.")
    @Size(min = 2, max = 250, message = "Description must be minimum of 2 characters long.")
    private String description;
}
