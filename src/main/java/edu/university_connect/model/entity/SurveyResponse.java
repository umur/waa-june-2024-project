package edu.university_connect.model.entity;

import edu.university_connect.model.entity.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SurveyResponse extends MetaData {
    @Id
    private int id;
    private String response;

    @ManyToOne(fetch = FetchType.LAZY)
    private  SurveyQuestionnaire surveyQuestionnaire;

}
