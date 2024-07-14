package edu.university_connect.model.contract.request.discussionthread;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReplyRequest {
    @NotNull @NotBlank private String content;
}
