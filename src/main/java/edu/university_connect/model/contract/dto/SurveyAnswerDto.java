package edu.university_connect.model.contract.dto;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SurveyAnswerDto {
    private  Long id;
    private String response;
    private LocalDate dueDate;
    LocalDateTime createdAt;
    String createdBy;
    LocalDateTime lastModifiedAt;
    String lastModifiedBy;
}
