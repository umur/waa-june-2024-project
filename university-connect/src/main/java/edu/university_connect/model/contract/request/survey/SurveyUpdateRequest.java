package edu.university_connect.model.contract.request.survey;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
@Data
public class SurveyUpdateRequest {
    @NotBlank
    @NotEmpty
    private String title;
}
