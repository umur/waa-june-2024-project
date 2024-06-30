package edu.university_connect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SurveyResponse {
    @Id
    private int id;
    private String response;

    @ManyToOne(fetch = FetchType.LAZY)
    private  SurveyQuestionnaire surveyQuestionnaire;

}
