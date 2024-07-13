package edu.university_connect.model.contract.request.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ResourceDownloadRequest {
    @NotBlank(message = "should not be empty")
    @NotNull(message = "is required")
    private String filename;
}
