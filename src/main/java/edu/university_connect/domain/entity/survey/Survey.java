package edu.university_connect.domain.entity.survey;

import edu.university_connect.domain.entity.User;
import edu.university_connect.model.contract.dto.UserDto;
import edu.university_connect.domain.entity.Resource;
import edu.university_connect.domain.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Survey extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(nullable = false)
    private  String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User creator;

    @OneToMany(mappedBy = "survey")
    private List<SurveyQuestion> surveyQuestionnaire;
}
