package edu.university_connect.domain.entity.survey;

import edu.university_connect.domain.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class SurveyQuestion extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(nullable = false)
    private String question;
    private LocalDate dueDate;

    @ManyToOne
    private Survey survey;

    @OneToMany(mappedBy = "surveyQuestion")
    private List<SurveyResponse> surveyResponse;
}
