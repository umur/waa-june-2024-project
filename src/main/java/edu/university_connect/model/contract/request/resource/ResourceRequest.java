package edu.university_connect.model.contract.request.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResourceRequest {
    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String title;

    private String description;

}
