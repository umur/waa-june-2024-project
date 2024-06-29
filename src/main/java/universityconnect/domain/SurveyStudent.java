package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class SurveyStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany
    @JoinColumn(name = "survey_student_id")
    private List<Answer> answers;
}
