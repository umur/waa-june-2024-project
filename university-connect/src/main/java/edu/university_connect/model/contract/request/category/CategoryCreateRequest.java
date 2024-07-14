package edu.university_connect.model.contract.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryCreateRequest {
    @NotBlank
    @NotEmpty
    private String title;
}
