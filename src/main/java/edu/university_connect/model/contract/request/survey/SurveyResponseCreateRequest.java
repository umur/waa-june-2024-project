package edu.university_connect.model.contract.request.survey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SurveyResponseCreateRequest {

    private  Long surveyQuestionId;
    @NotBlank
    @NotEmpty
    private String response;
    private LocalDate submissionDate;
}
