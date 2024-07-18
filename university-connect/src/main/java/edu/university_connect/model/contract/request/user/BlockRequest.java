package edu.university_connect.model.contract.request.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlockRequest {

    @NotNull(message = "is required")
    private Long userId;
}
