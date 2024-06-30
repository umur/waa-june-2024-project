package edu.university_connect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private  User creator;

    @OneToMany
    @JoinColumn(name="survey_id")
    private List<SurveyQuestionnaire> surveyQuestionnaire;
}
