package edu.university_connect.model.contract.dto;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SurveyDto {
    private  Long id;
    private  String title;
    LocalDateTime createdAt;
    String createdBy;
    LocalDateTime lastModifiedAt;
    String lastModifiedBy;
}
