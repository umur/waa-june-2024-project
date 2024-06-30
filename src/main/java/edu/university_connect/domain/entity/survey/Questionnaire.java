package edu.university_connect.domain.entity.survey;

import edu.university_connect.domain.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Questionnaire extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(nullable = false)
    private String question;

    @OneToMany(mappedBy = "surveyQuestionnaire")
    private List<Response> surveyResponse;
}
