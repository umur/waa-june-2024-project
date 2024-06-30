package edu.university_connect.model.entity;

import edu.university_connect.model.entity.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Survey extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(nullable = false)
    private  String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User creator;

    @OneToMany
    @JoinColumn(name="survey_id")
    private List<SurveyQuestionnaire> surveyQuestionnaire;
}
