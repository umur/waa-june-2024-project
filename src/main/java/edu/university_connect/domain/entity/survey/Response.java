package edu.university_connect.domain.entity.survey;

import edu.university_connect.model.contract.dto.UserDto;
import edu.university_connect.domain.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Response extends MetaData {
    @Id
    private int id;

    @Column(nullable = false)
    private String response;

    @ManyToOne(fetch = FetchType.LAZY)
    private Questionnaire surveyQuestionnaire;

}
