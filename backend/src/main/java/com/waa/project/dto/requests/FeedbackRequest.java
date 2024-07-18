package com.waa.project.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FeedbackRequest {
    @NotNull(message = "Title cannot be null.")
    @NotBlank(message = "Title cannot be blank.")
    @Size(min = 2, max = 250, message = "Title must be minimum of 2 characters long.")
    private String title;

    @NotNull(message = "Description cannot be null.")
    @NotBlank(message = "Description cannot be blank.")
    @Size(min = 2, max = 250, message = "Description must be minimum of 2 characters long.")
    private String body;

    @NotNull(message = "Category cannot be empty.")
    private Long feedbackCategory;
}
