package edu.university_connect.model.contract.request.discussionthread;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PostRequest {
    @NotNull @NotBlank private String content;
    @NotNull private Long categoryId;
}
