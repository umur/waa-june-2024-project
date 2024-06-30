package edu.university_connect.model.entity;

import edu.university_connect.model.entity.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class SurveyQuestionnaire extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String question;

    @OneToMany(mappedBy = "surveyQuestionnaire")
    private List<SurveyResponse> surveyResponse;
}
