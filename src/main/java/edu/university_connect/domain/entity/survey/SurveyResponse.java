package edu.university_connect.domain.entity.survey;

import edu.university_connect.domain.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class SurveyResponse extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String response;

    private LocalDate submissionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyQuestion surveyQuestion;

}
